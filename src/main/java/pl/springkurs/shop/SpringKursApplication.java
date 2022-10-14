package pl.springkurs.shop;

import lombok.extern.java.Log;
import org.javamoney.moneta.FastMoney;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.springkurs.shop.payments.domain.PaymentRequest;
import pl.springkurs.shop.payments.ports.PaymentService;

@Log
public class SpringKursApplication {

    private static final String DEFAULT_CURRENCY_CODE = "PLN";

    public static void main(String[] args) {

        try (var container = new AnnotationConfigApplicationContext(ShopConfiguration.class)) {
            var paymentService = container.getBean(PaymentService.class);
            var paymentRequest = new PaymentRequest(1L, FastMoney.of(1_000, DEFAULT_CURRENCY_CODE));
            var payment = paymentService.process(paymentRequest);
            log.info(paymentService.getById(payment.getId()).toString());

        }

    }

}
