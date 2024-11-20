package com.task.coupon.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.task.coupon.model.interfaces.CouponDetails;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Coupon {


    @Id
    private String id;
    @JsonProperty("type")
    private String type;
    @JsonProperty("details")
    private CouponDetails details;

    // Getters and Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CouponDetails getDetails() {
        return details;
    }

    public void setDetails(CouponDetails details) {
        this.details = details;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
