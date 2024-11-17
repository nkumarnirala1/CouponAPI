package com.task.coupon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class UpdatedCartWitFinalAmount {

    UpdatedCart updated_cart;

    public UpdatedCart getUpdatedCart() {
        return updated_cart;
    }

    public void setUpdatedCart(UpdatedCart updated_cart) {
        this.updated_cart = updated_cart;
    }

}
