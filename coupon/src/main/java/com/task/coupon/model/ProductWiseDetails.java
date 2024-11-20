package com.task.coupon.model;

import com.task.coupon.model.interfaces.CouponDetails;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class ProductWiseDetails extends CouponDetails {

    private String product_id;
    private int discount;

    // Getters and Setters
    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
