package com.scaler.paymentgatewayintegration.PaymentServices;

import com.scaler.paymentgatewayintegration.PaymentDTOS.GeneratePaymentLinkRequestDTO;
import com.stripe.exception.StripeException;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {

    public String generatePaymentLink(Long orderId) throws JSONException, StripeException;

}
