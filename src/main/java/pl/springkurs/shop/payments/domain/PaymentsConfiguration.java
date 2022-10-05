package pl.springkurs.shop.payments.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.springkurs.shop.payments.adapters.logging.ConsolePaymentLogger;
import pl.springkurs.shop.payments.adapters.persistence.jpa.JpaPaymentRepository;
import pl.springkurs.shop.payments.ports.PaymentRepository;
import pl.springkurs.shop.payments.ports.PaymentService;
import pl.springkurs.shop.time.TimeProvider;

@Configuration
public class PaymentsConfiguration {

    @Bean
    public PaymentFeeCalculator paymentFeeCalculator() {
        return new PercentagePaymentFeeCalculator(0.01);
    }

    @Bean
    public PaymentIdGenerator paymentIdGenerator() {
        return new UuidPaymentIdGenerator();
    }

    @Bean
    public PaymentRepository paymentRepository() {
        return new JpaPaymentRepository();
    }

    @Bean
    public PaymentService paymentService(PaymentIdGenerator paymentIdGenerator, PaymentFeeCalculator paymentFeeCalculator, PaymentRepository paymentRepository, TimeProvider timeProvider) {
        return new PaymentProcessor(paymentIdGenerator,paymentFeeCalculator, paymentRepository, timeProvider);
    }

    //@Scope(BeanDefinition.SCOPE_SINGLETON) //default
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public ConsolePaymentLogger paymentLogger() {
        return new ConsolePaymentLogger();
    }

}
