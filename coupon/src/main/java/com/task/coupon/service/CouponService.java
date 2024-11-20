package com.task.coupon.service;

import com.task.coupon.dao.CouponRepo;
import com.task.coupon.model.*;
import com.task.coupon.utility.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CouponService {

    @Autowired
    private CouponRepo couponRepo;

    @Autowired
    private HandleCartWiseCoupon applyCartWiseCoupon;

    @Autowired
    private HandleProductWiseCoupon applyProductCoupon;

    @Autowired
    private HandleBxGyCoupon applyBxGyCoupon;

    Logger log = LoggerFactory.getLogger(CouponService.class);

    public UpdatedCartWitFinalAmount applyCouponOnCart(Customer customer, String couponId) {

        UpdatedCartWitFinalAmount updatedCartWitFinalAmount = null;

        System.out.println("couponDd = " + couponId + " && cart " + customer.getCart());
        Coupon coupon = couponRepo.fetchCouponById(couponId);

        System.out.println("fetched coupon = " + coupon);

        if (Constant.CART_WISE.equalsIgnoreCase(coupon.getType())) {
            updatedCartWitFinalAmount = applyCartWiseCoupon.calculateDiscount(coupon, customer.getCart());
        } else if (Constant.PRODUCT_WISE.equalsIgnoreCase(coupon.getType())) {
            updatedCartWitFinalAmount = applyProductCoupon.calculateDiscount(coupon, customer.getCart());
        } else if (Constant.BXGY.equalsIgnoreCase(coupon.getType())) {
            updatedCartWitFinalAmount = applyBxGyCoupon.calculateDiscount(coupon, customer.getCart());
        } else {
            System.out.println("Invalid coupon");//TODO
        }


        return updatedCartWitFinalAmount;
    }

    public void save(Coupon coupon) {
        couponRepo.addCoupon(coupon);
    }

    public List<Coupon> show() {
        return couponRepo.fetchCoupons();
    }


    public List<Coupon> applicableCoupon(Customer customer) {

        double totalPrice = Double.valueOf(0);

        List<Item> items = customer.getCart().getItems();

        totalPrice += items.stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();

        System.out.println("total price is = " + totalPrice);

        List<Coupon> avilableCoupons = couponRepo.fetchCoupons();


        double finalTotalPrice = totalPrice;
        List<Coupon> applicableCoupons = new ArrayList<>();
        for(Coupon coupon :applicableCoupons)
        {

        }
                avilableCoupons.stream().filter(coupon -> ((CartWiseDetails) coupon.getDetails()).getThreshold() < finalTotalPrice).toList();

        return applicableCoupons;
    }

    public Coupon getCouponById(String id) {

        return couponRepo.fetchCouponById(id);
    }
}
