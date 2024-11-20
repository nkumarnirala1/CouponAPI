package com.task.coupon.dao;


import com.task.coupon.model.CartWiseDetails;
import com.task.coupon.model.Coupon;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import com.task.coupon.model.Item;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class CouponRepo {

    List<Item> items = new ArrayList<>();
   public static List<Coupon> coupons = new ArrayList<>();

    public List<Item> addItem() {

        Item item = new Item();

        item.setProduct_id("1");
        item.setPrice(100);
        item.setQuantity(2);
        items.add(item);

        item = new Item();
        item.setProduct_id("2");
        item.setPrice(200);
        item.setQuantity(1);

        items.add(item);

        return items;

    }

    public void addCoupon(Coupon coupon) {
        coupons.add(coupon);

    }

    public List<Coupon> fetchCoupons() {

        return coupons;
    }

    public Coupon fetchCouponById(String couponId) {
        return fetchCoupons().stream()
                .filter(coupon -> couponId.equalsIgnoreCase(coupon.getId()))
                .findFirst() // Fetches the first matching coupon
                .orElse(null); // Returns null if no coupon matches
    }
}
