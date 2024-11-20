package com.task.coupon;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.coupon.controller.CouponController;
import com.task.coupon.Repo.CouponRepo;
import com.task.coupon.model.Coupon;
import com.task.coupon.model.Customer;
import com.task.coupon.model.UpdatedCartWitFinalAmount;
import com.task.coupon.service.CouponService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;
import java.util.List;

@SpringBootTest
class CouponApiApplicationTests {

    @Autowired
    private CouponController couponController;

    @Autowired
    private CouponService couponService;

    final static ObjectMapper objectMapper = new ObjectMapper();

    public static List<Coupon> readCouponFromJson(String filePath) throws IOException {
        return objectMapper.readValue(new File(filePath), new TypeReference<List<Coupon>>() {
        });
    }

    final static ObjectMapper objectMapperTest = new ObjectMapper();


    public static List<Coupon> readCouponFromJson() throws IOException {

        String filePath = "src/test/resources/coupon.json";
        return objectMapperTest.readValue(new File(filePath), new TypeReference<List<Coupon>>() {
        });
    }


    void loadCoupons() throws IOException {
        CouponRepo.coupons.addAll(readCouponFromJson());
    }

    @Test
    void getAllCouponsTest() throws IOException {

        loadCoupons();
        ResponseEntity<List<Coupon>> responseEntity = couponController.getCoupon();

        List<Coupon> couponList = responseEntity.getBody();
        assert (couponList.size() == 3);
    }

    @Test
    void applyBxGyCouponTest() throws IOException {
        loadCoupons();
        String filePath = "src/test/resources/ApplyCouponBxGy.json";
        Customer customer = objectMapperTest.readValue(new File(filePath), Customer.class);

        ResponseEntity<UpdatedCartWitFinalAmount> response = couponController.ApplyCoupon(customer, "3");

        UpdatedCartWitFinalAmount updatedCartWitFinalAmount = response.getBody();
        assert (updatedCartWitFinalAmount.getUpdatedCart().getTotalDiscount() == 50);

    }

}
