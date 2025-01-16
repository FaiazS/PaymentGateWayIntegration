package com.scaler.paymentgatewayintegration.PaymentServices;

import com.razorpay.PaymentLink;
import com.scaler.paymentgatewayintegration.PaymentConfigurations.RazorPayClient;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

@Service("razorpay")
public abstract class RazorPayPaymentGatewayIntegrationService implements PaymentService {

    private RazorPayClient razorPayClient;

    public RazorPayPaymentGatewayIntegrationService (RazorPayClient razorPayClient){

        this.razorPayClient = razorPayClient;

    }

    @Override
    public String generatePaymentLink(Long orderId) throws JSONException {

        JSONObject paymentRequestLink = new JSONObject();

        paymentRequestLink.put("amount", 1000);
        paymentRequestLink.put("currency","INR");
       // paymentRequestLink.put("accept_partial", true);
       // paymentRequestLink.put("first_min_partial_amount", 100);
        paymentRequestLink.put("expire_by", System.currentTimeMillis() + 20 * 60 * 1000);
        paymentRequestLink.put("reference_id", orderId.toString());
        paymentRequestLink.put("description", "Demo Payment for Project");

        JSONObject customerDetails = new JSONObject();

        customerDetails.put("name","Faiaz Ahmed");
        customerDetails.put("contact","+91 7810907810");
        customerDetails.put("email","faiazrex8@gmail.com");

        paymentRequestLink.put("customer", customerDetails);

        JSONObject notifyUpdate = new JSONObject();

        notifyUpdate.put("email",true);
        notifyUpdate.put("sms",true);

        paymentRequestLink.put("notifyUpdate", notifyUpdate);

        paymentRequestLink.put("notifcation_enable", true);

       // JSONObject paymentNotes = new JSONObject();

       // paymentNotes.put("policy_name","Faiaz Ahmed");

       // paymentRequestLink.put("notes", paymentNotes);

        paymentRequestLink.put("callback_url", "www.scaler.com");
        paymentRequestLink.put("callback_method", "get");

        PaymentLink paymentLink = razorPayClient.paymentLink.create(paymentRequestLink);

        return paymentLink.toString();

    }
}
