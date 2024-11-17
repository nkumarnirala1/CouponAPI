package com.task.coupon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class UpdatedCart {

    List<Item> items;

    Double total_discount;
    Double final_price;
    Double total_price;
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Double getTotalPrice() {
        return total_price;
    }

    public Double getFinalPrice() {
        return final_price;
    }

    public void setFinalPrice(Double final_price) {
        this.final_price = final_price;
    }

    public void setTotalPrice(Double total_price) {
        this.total_price = total_price;
    }


    public Double getTotalDiscount() {
        return total_discount;
    }

    public void setTotalDiscount(Double total_discount) {
        this.total_discount = total_discount;
    }


}
