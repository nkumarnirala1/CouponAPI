package com.task.coupon.service;

import com.task.coupon.model.*;
import com.task.coupon.utility.CalculateDiscountUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HandleCartWiseCoupon {


    public UpdatedCartWitFinalAmount calculateDiscount(Coupon coupon, Cart cart) {
        double totalPrice = Double.valueOf(0);


        CartWiseDetails cartWiseDetails = (CartWiseDetails) coupon.getDetails();
        List<Item> items = cart.getItems();

        for (Item item : items) {
            totalPrice += item.getPrice() * item.getQuantity();
        }

        double discount = 0;
        if (totalPrice >= cartWiseDetails.getThreshold()) {
            discount = CalculateDiscountUtility.calculateDiscount(totalPrice, cartWiseDetails.getDiscount());
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
