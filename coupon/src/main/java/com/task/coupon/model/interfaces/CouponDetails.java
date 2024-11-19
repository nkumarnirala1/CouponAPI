package com.task.coupon.model.interfaces;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.task.coupon.model.BxgyDetails;
import com.task.coupon.model.CartWiseDetails;
import com.task.coupon.model.ProductWiseDetails;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CartWiseDetails.class, name = "cart-wise"),
        @JsonSubTypes.Type(value = ProductWiseDetails.class, name = "product-wise"),
        @JsonSubTypes.Type(value = BxgyDetails.class, name = "bxgy")
})
public abstract class CouponDetails { }


