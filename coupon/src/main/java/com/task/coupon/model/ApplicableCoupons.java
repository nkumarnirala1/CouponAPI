package com.task.coupon.model;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class ApplicableCoupons {
    List<Coupon> applicable_coupons;

    public List<Coupon> getApplicable_coupons() {
        return applicable_coupons;
    }

    public void setApplicable_coupons(List<Coupon> applicable_coupons) {
        this.applicable_coupons = applicable_coupons;
    }

}
