package com.task.coupon.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private String product_id;
    private int quantity;

    private double price;
    private double totalDiscount;

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
}
