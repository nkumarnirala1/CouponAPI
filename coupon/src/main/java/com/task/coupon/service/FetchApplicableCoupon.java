package com.task.coupon.service;

import com.task.coupon.dao.CouponRepo;
import com.task.coupon.model.Cart;
import com.task.coupon.model.Coupon;
import com.task.coupon.model.Customer;
import com.task.coupon.model.UpdatedCartWitFinalAmount;
import com.task.coupon.utility.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FetchApplicableCoupon {


    @Autowired
    private CouponRepo couponRepo;

    @Autowired
    private HandleCartWiseCoupon applyCartWiseCoupon;

    @Autowired
    private HandleProductWiseCoupon applyProductCoupon;

    @Autowired
    private HandleBxGyCoupon applyBxGyCoupon;

    public List<Coupon> fetchApplicableCoupon(Customer customer) {
        List<Coupon> applicableCoupon = new ArrayList<>();
        List<Coupon> avilableCoupons = couponRepo.fetchCoupons();

        Cart cart = customer.getCart();

        UpdatedCartWitFinalAmount updatedCartWitFinalAmount = null;

        for (Coupon coupon : avilableCoupons) {
            if (Constant.CART_WISE.equalsIgnoreCase(coupon.getType())) {
                updatedCartWitFinalAmount = applyCartWiseCoupon.calculateDiscount(coupon, customer.getCart());
            } else if (Constant.PRODUCT_WISE.equalsIgnoreCase(coupon.getType())) {
                updatedCartWitFinalAmount = applyProductCoupon.calculateDiscount(coupon, customer.getCart());
            } else if (Constant.BXGY.equalsIgnoreCase(coupon.getType())) {
                updatedCartWitFinalAmount = applyBxGyCoupon.calculateDiscount(coupon, customer.getCart());
            } else {
                System.out.println("Invalid coupon");//TODO
            }
            if(null!=updatedCartWitFinalAmount && null!= updatedCartWitFinalAmount.getUpdatedCart() && updatedCartWitFinalAmount.getUpdatedCart().getTotalDiscount()>0)
            {
                applicableCoupon.add(coupon);
            }
        }

        return applicableCoupon;

    }
}
