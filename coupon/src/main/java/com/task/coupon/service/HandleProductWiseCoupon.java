package com.task.coupon.service;

import com.task.coupon.model.*;
import com.task.coupon.utility.CalculateDiscountUtility;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HandleProductWiseCoupon {

    public UpdatedCartWitFinalAmount calculateDiscount(Coupon coupon, Cart cart) {

        double totalPrice = Double.valueOf(0);


        ProductWiseDetails details = (ProductWiseDetails) coupon.getDetails();
        List<Item> items = cart.getItems();

        double product_price = 0;
        double totalPriceapplicableforDiscount = 0;
        int countProduct = 0;
        for (Item item : items) {
            if (item.getProduct_id().equalsIgnoreCase(details.getProduct_id())) {
                countProduct += item.getQuantity();
                product_price = item.getPrice();
            }

            totalPrice += item.getPrice() * item.getQuantity();
        }
        double discount = 0;

        if (countProduct > 0) {
            totalPriceapplicableforDiscount = countProduct * product_price;


            discount = CalculateDiscountUtility.calculateDiscountByPercentage(totalPriceapplicableforDiscount, details.getDiscount());

        }
        UpdatedCartWitFinalAmount updatedCartWitFinalAmount = new UpdatedCartWitFinalAmount();

        UpdatedCart updatedCart = new UpdatedCart();

        updatedCart.setTotalPrice(totalPrice);
        updatedCart.setTotalDiscount(discount);
        updatedCart.setFinalPrice(totalPrice - discount);
        updatedCart.setItems(items);
        updatedCartWitFinalAmount.setUpdatedCart(updatedCart);
        return updatedCartWitFinalAmount;
    }
}
