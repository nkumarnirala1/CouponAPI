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
    public void addCoupon(@RequestBody Coupon coupon)
    {
        System.out.println(coupon);
        if(coupon!=null)
        {
            System.out.println(coupon.getType());
            System.out.println(coupon.getDetails());


        }
        couponService.save(coupon);
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
