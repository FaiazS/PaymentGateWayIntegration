package com.scaler.paymentgatewayintegration.PaymentContollers;

import com.scaler.paymentgatewayintegration.PaymentDTOS.GeneratePaymentLinkRequestDTO;
import com.scaler.paymentgatewayintegration.PaymentServices.PaymentService;
import com.scaler.paymentgatewayintegration.PaymentServices.RazorPayPaymentGatewayIntegrationService;
import com.scaler.paymentgatewayintegration.PaymentServices.StripePaymentGateWayIntegrationService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private RazorPayPaymentGatewayIntegrationService razorPayPaymentGatewayIntegrationService;

    private StripePaymentGateWayIntegrationService stripePaymentGateWayIntegrationService;

    private int counter = 0;

    public PaymentController(RazorPayPaymentGatewayIntegrationService razorPayPaymentGatewayIntegrationService,StripePaymentGateWayIntegrationService stripePaymentGateWayIntegrationService) {

        this.razorPayPaymentGatewayIntegrationService = razorPayPaymentGatewayIntegrationService;
        this.stripePaymentGateWayIntegrationService = stripePaymentGateWayIntegrationService;

    }

    @PostMapping("/payments")
    public String generatePaymentLink(@RequestBody GeneratePaymentLinkRequestDTO generatePaymentLinkRequestDTO) throws JSONException, StripeException {

        if(counter % 2 == 0){

            counter += 1;
            return razorPayPaymentGatewayIntegrationService.generatePaymentLink(generatePaymentLinkRequestDTO.getOrderId());
        }
        else{

            counter += 1;
            return stripePaymentGateWayIntegrationService.generatePaymentLink(generatePaymentLinkRequestDTO.getOrderId());
        }
    }
    }

