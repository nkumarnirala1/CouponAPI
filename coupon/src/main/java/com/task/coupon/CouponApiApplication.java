package com.task.coupon;

import com.task.coupon.dao.CouponRepo;
import com.task.coupon.model.Coupon;
import com.task.coupon.utility.CouponLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.task.coupon.service.CouponService;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class CouponApiApplication {

	@Autowired
    static CouponService couponService;
	public static void main(String[] args) throws IOException {
		ApplicationContext context= SpringApplication.run(CouponApiApplication.class, args);

		List<Coupon> couponList = CouponLoader.readCouponFromJson();

		CouponRepo.coupons.addAll(couponList);


	}

}
