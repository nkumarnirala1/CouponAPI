package com.task.coupon.controller;

import com.task.coupon.model.*;
import com.task.coupon.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CouponController {


    @Autowired
    CouponService couponService;

    @PostMapping("/coupons")
    public ResponseEntity<String> addCoupon(@RequestBody Coupon coupon) {
        if ("cart-wise".equals(coupon.getType())) {
            CartWiseDetails details = (CartWiseDetails) coupon.getDetails();
            couponService.save(coupon);
            return ResponseEntity.ok("Processed cart-wise coupon with threshold: " +
                    details.getThreshold() + " and discount: " + details.getDiscount());
        } else if ("product-wise".equals(coupon.getType())) {
            ProductWiseDetails details = (ProductWiseDetails) coupon.getDetails();
            couponService.save(coupon);
            return ResponseEntity.ok("Processed product-wise coupon for product ID: " +
                    details.getProduct_id() + " and discount: " + details.getDiscount());
        } else if ("bxgy".equals(coupon.getType())) {
            couponService.save(coupon);
            return ResponseEntity.ok("Processed bxgy coupon");
        } else {
            return ResponseEntity.badRequest().body("Invalid coupon type");
        }


    }


    @GetMapping("/coupons")
    public ResponseEntity<List<Coupon>> getCoupon() {
        return ResponseEntity.ok(couponService.show());
    }

    @PostMapping("/applicable-coupons")
    public ResponseEntity<List<Coupon>> getApplocableCoupon(@RequestBody Customer customer) {
        return ResponseEntity.ok(couponService.applicableCoupon(customer));
    }

    @PostMapping("/apply-coupon/{id}")
    public ResponseEntity<UpdatedCartWitFinalAmount> ApplyCoupon(@RequestBody Customer customer, @PathVariable String id) {
        //System.out.println("id is="+id);

        return ResponseEntity.ok(couponService.applyCouponOnCart(customer, id));
    }

    @GetMapping("/coupon/{id}")
    public ResponseEntity<Coupon> getCouponById(@PathVariable String id) {
        return ResponseEntity.ok(couponService.getCouponById(id));
    }

}
