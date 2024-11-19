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
    /*
    { "type": "cart-wise" , "details": { "threshold": 100 , "discount": 10 } }
     */
    List<Item> items = new ArrayList<>();
    List<Coupon> coupons = new ArrayList<>();

    public List<Item> addItem() {

        Item item = new Item();

        item.setProductId("1");
        item.setPrice(100);
        item.setQuantity(2);
        items.add(item);

        item = new Item();
        item.setProductId("2");
        item.setPrice(200);
        item.setQuantity(1);

        items.add(item);

        return items;

    }

    public void addCoupon(Coupon coupon2) {


        Thread thread = Thread.currentThread();
        System.out.println("get coupon Thread: " + thread.getName());
        coupons.add(coupon2);
        loader();

    }

    public List<Coupon> fetchCoupons() {

        Thread thread = Thread.currentThread();
        System.out.println("fetch coupon Thread: " + thread.getName());
        return coupons;
    }

    public Coupon fetchCouponById(String couponId) {
        return fetchCoupons().stream()
                .filter(coupon -> couponId.equalsIgnoreCase(coupon.getId()))
                .findFirst() // Fetches the first matching coupon
                .orElse(null); // Returns null if no coupon matches
    }

    public void loader() {

        Coupon coupon = new Coupon();
        coupon.setType("cart-wise");

        CartWiseDetails details = new CartWiseDetails();
        details.setThreshold(100);
        details.setDiscount(10);
        coupon.setDetails(details);
        coupon.setId("11");
        coupons.add(coupon);

        coupon = new Coupon();
        details = new CartWiseDetails();
        coupon.setType("cart-wise");
        details.setThreshold(150);
        details.setDiscount(50);
        coupon.setDetails(details);
        coupon.setId("12");
        coupons.add(coupon);
    }
}
