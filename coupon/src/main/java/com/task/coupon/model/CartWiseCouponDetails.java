package com.task.coupon.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class CartWiseCouponDetails {

    Integer  threshold;
    Integer discount;


    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public  Integer getDiscount() {
        return discount;
    }

    public void setDiscount( Integer discount) {
        this.discount = discount;
    }

}
