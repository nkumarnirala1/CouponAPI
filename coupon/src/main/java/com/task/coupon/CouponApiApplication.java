package com.task.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.task.coupon.service.CouponService;

@SpringBootApplication
public class CouponApiApplication {


	public static void main(String[] args) {
		ApplicationContext context= SpringApplication.run(CouponApiApplication.class, args);

//		CouponService couponService = context.getBean(CouponService.class);
//
//		couponService.save();
//		couponService.show();


	}

}
