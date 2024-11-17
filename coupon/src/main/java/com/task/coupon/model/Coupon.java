package com.task.coupon.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {


    String Id;
    String type;
    CartWiseCouponDetails details;
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CartWiseCouponDetails getDetails() {
        return details;
    }

    public void setDetails(CartWiseCouponDetails details) {
        this.details = details;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }




}
