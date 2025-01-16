package com.scaler.paymentgatewayintegration.PaymentDTOS;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class GeneratePaymentLinkRequestDTO {

    private Long orderId;

    public void setOrderId(Long orderId){

        this.orderId = orderId;
    }

    public Long getOrderId(){

        return orderId;
    }
}
