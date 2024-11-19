package com.task.coupon.model;

import com.task.coupon.model.interfaces.CouponDetails;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class BxgyDetails extends CouponDetails {

    private List<ProductQuantity> buy_products;
    private List<ProductQuantity> get_products;
    private int repition_limit;

    // Getters and Setters
    public List<ProductQuantity> getBuy_products() {
        return buy_products;
    }

    public void setBuy_products(List<ProductQuantity> buy_products) {
        this.buy_products = buy_products;
    }

    public List<ProductQuantity> getGet_products() {
        return get_products;
    }

    public void setGet_products(List<ProductQuantity> get_products) {
        this.get_products = get_products;
    }

    public int getRepition_limit() {
        return repition_limit;
    }

    public void setRepition_limit(int repition_limit) {
        this.repition_limit = repition_limit;
    }
}
