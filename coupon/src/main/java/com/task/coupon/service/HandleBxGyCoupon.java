package com.task.coupon.service;

import com.task.coupon.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class HandleBxGyCoupon {

    public UpdatedCartWitFinalAmount calculateDiscount(Coupon coupon, Cart cart) {

        double totalPrice = Double.valueOf(0);

        BxgyDetails bxgyDetails = (BxgyDetails) coupon.getDetails();
        int repetionLimit = bxgyDetails.getRepition_limit();
        List<Item> itemList = cart.getItems();
        HashMap<String, ProductQuantity> buyProductMap = new HashMap<>();
        HashMap<String, ProductQuantity> getProductMap = new HashMap<>();

        for (ProductQuantity productQuantity : bxgyDetails.getBuy_products()) {
            buyProductMap.put(productQuantity.getProduct_id(), productQuantity);
        }

        for (ProductQuantity productQuantity : bxgyDetails.getGet_products()) {
            getProductMap.put(productQuantity.getProduct_id(), productQuantity);
        }


        List<String> productIdsApplicableForDiscount = new ArrayList<>();
        int noOfTimesOfferApplicable = checkIfBxGyCouponApplicable(productIdsApplicableForDiscount, getProductMap, buyProductMap, itemList, repetionLimit);

        double discount = 0;
        //check if bxgy coupon is applicable
        if (noOfTimesOfferApplicable > 0 && productIdsApplicableForDiscount.size() > 0) {
            for (Item item : itemList) {

                if (productIdsApplicableForDiscount.contains(item.getProduct_id())) {
                    int offeredQuantity = (getProductMap.get(item.getProduct_id())).getQuantity();
                    int buyQuantity = item.getQuantity();
                    if (buyQuantity < offeredQuantity) {
                        discount += item.getPrice() * buyQuantity;
                    } else {
                        int n = buyQuantity / offeredQuantity;
                        if (n <= noOfTimesOfferApplicable) {
                            discount += item.getPrice() * buyQuantity;
                            noOfTimesOfferApplicable -= n;
                        } else {
                            discount += item.getPrice() * noOfTimesOfferApplicable;
                        }

                    }

                }

                totalPrice+=item.getPrice()*item.getQuantity();
            }
        }
        UpdatedCartWitFinalAmount updatedCartWitFinalAmount = new UpdatedCartWitFinalAmount();

        UpdatedCart updatedCart = new UpdatedCart();

        updatedCart.setTotalPrice(totalPrice);
        updatedCart.setTotalDiscount(discount);
        updatedCart.setFinalPrice(totalPrice - discount);
        updatedCart.setItems(itemList);
        updatedCartWitFinalAmount.setUpdatedCart(updatedCart);
        return updatedCartWitFinalAmount;
    }

    public int checkIfBxGyCouponApplicable(List<String> productIdsApplicableForDiscount, HashMap<String, ProductQuantity> getProductMap, HashMap<String, ProductQuantity> buyProductMap, List<Item> itemList, int repetionLimit) {

        int offerCount = 0;

        for (Item item : itemList) {

            if (offerCount < repetionLimit && buyProductMap.containsKey(item.getProduct_id())) {
                ProductQuantity productQuantity = buyProductMap.get(item.getProduct_id());

                int offerQuantity = productQuantity.getQuantity();
                int boughtQuantity = item.getQuantity();

                if (boughtQuantity > offerQuantity) {
                    offerCount += (boughtQuantity / offerQuantity);

                }

            }
        }

        if (offerCount > 0) {
            offerCount = offerCount > repetionLimit ? repetionLimit : offerCount;

            for (Item item : itemList) {
                if (getProductMap.containsKey(item.getProduct_id())) {
                    productIdsApplicableForDiscount.add(item.getProduct_id());
                }
            }

        }

        return offerCount;


    }

}
