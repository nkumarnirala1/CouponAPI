package com.task.coupon.utility;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.coupon.model.Coupon;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class CouponLoader {

    final static ObjectMapper objectMapper = new ObjectMapper();

    public static List<Coupon> readCouponFromJson() throws IOException {

        String filePath = "coupon/src/main/resources/coupon.json";
        return objectMapper.readValue(new File(filePath), new TypeReference<List<Coupon>>() {
        });
    }


}
