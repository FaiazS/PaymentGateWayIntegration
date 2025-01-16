package com.scaler.paymentgatewayintegration.PaymentConfigurations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;


@Configuration

public class RazorPayClient {

    @Value("${razorpay.key.id")//imagineryvalue post razorpayaccount.
    private String razorPayKeyID;

    @Value("${razorpay.keysecret")// imagineryvalue post razorpayaccount.
    private String razorPayKeySecret;

    @Bean
    public RazorPayClient instantiateRazorPayClient() throws RazorpayException {

        return new RazorPayClient();

    }

}
