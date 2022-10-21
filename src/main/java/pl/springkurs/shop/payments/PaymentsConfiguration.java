package pl.springkurs.shop.payments;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import pl.springkurs.shop.payments.domain.*;
import pl.springkurs.shop.payments.ports.PaymentRepository;
import pl.springkurs.shop.payments.ports.PaymentService;
import pl.springkurs.shop.payments.ports.PaymentsServiceFactory;
import pl.springkurs.shop.payments.ports.TimeProvider;

@Configuration
public class PaymentsConfiguration {

    private static final PaymentsServiceFactory PAYMENT_SERVICE_FACTORY = new DefaultPaymentServiceFactory();

    @Bean
    public PaymentService paymentService(PaymentRepository paymentRepository, TimeProvider timeProvider) {
        return PAYMENT_SERVICE_FACTORY.create(paymentRepository, timeProvider);
    }

    @Primary
    @Bean
    public PaymentService paymentServiceDecorator(PaymentService paymentService) {
        return new PaymentServiceDecorator(paymentService);
    }

}
