package com.scaler.paymentgatewayintegration.PaymentServices;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.stereotype.Service;

@Service("stripe")
public abstract class StripePaymentGateWayIntegrationService implements PaymentService {

    @Override
    public String generatePaymentLink(Long orderId) throws StripeException {

        Stripe.apiKey = "sk_test_26PHem9AhJZvU623DfE1x4sd";

        PriceCreateParams priceCreateParams =
                PriceCreateParams.builder()
                        .setCurrency("INR")
                        .setUnitAmount(100000L)
                        .setRecurring(
                                PriceCreateParams.Recurring.builder()
                                        .setInterval(PriceCreateParams.Recurring.Interval.MONTH)
                                        .build()
                        )
                        .setProductData(
                                PriceCreateParams.ProductData.builder().setName("Payment Via Stripe").build()
                        )
                        .build();
        Price price = Price.create(priceCreateParams);

        PaymentLinkCreateParams params = PaymentLinkCreateParams.builder()
                                                                .addLineItem(PaymentLinkCreateParams.LineItem.builder()
                                                                .setPrice(priceCreateParams.toString())
                                                                .setQuantity(1L).build()
                        )
                .setAfterCompletion(PaymentLinkCreateParams.AfterCompletion.builder()
                        .setRedirect(
                                PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                        .setUrl("https://www.stripe.com")
                                        .build()
                        )
                        .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                        .build()
                )
                .build();

        PaymentLink paymentLink = PaymentLink.create(params);

        return paymentLink.toString();

    }
}
