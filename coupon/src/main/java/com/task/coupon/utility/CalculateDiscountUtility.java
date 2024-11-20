package com.task.coupon.utility;


import org.springframework.stereotype.Component;


public class CalculateDiscountUtility {

    public static double calculateDiscountByPercentage(double amount, double discount) {
        return amount * discount / 100;
    }

    public static double calculateDiscountedAmount(double amount, double discount) {
        return amount - calculateDiscountByPercentage(amount, discount);
    }

}
