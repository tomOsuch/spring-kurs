package pl.tomaszosuch.springkurs.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import pl.tomaszosuch.springkurs.shop.payments.PaymentFeeCalculator;
import pl.tomaszosuch.springkurs.shop.payments.PercentagePaymentFeeCalculator;

@EnableAspectJAutoProxy
@ComponentScan
@Configuration
public class ShopConfiguration {

    @Bean
    public PaymentFeeCalculator paymentFeeCalculator() {
        return new PercentagePaymentFeeCalculator(0.01);
    }
}
