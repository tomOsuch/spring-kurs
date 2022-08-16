package pl.tomaszosuch.springkurs.shop;

import lombok.extern.java.Log;
import org.javamoney.moneta.FastMoney;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.tomaszosuch.springkurs.shop.payments.*;
import pl.tomaszosuch.springkurs.shop.time.SystemTimeProvider;

@Log
public class SpringKursApplication {

    private static final String DEFAULT_CURRENCY_CODE = "PLN";

    public static void main(String[] args) {

        try (var container = new AnnotationConfigApplicationContext(ShopConfiguration.class)) {
            var paymentService = container.getBean(PaymentService.class);
            var paymentRequest = new PaymentRequest(1L, FastMoney.of(1_000, DEFAULT_CURRENCY_CODE));
            var payment = paymentService.process(paymentRequest);
            log.info(payment.toString());
        }

    }

}
