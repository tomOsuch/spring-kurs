package pl.springkurs.shop.payments.domain;

import pl.springkurs.shop.payments.ports.PaymentRepository;
import pl.springkurs.shop.payments.ports.PaymentService;
import pl.springkurs.shop.payments.ports.PaymentsServiceFactory;
import pl.springkurs.shop.payments.ports.TimeProvider;

public class DefaultPaymentServiceFactory implements PaymentsServiceFactory {

    private static final PaymentIdGenerator PAYMENT_ID_GENERATOR = new UuidPaymentIdGenerator();
    private static final PaymentFeeCalculator PAYMENT_FEE_CALCULATOR = new PercentagePaymentFeeCalculator(0.01);

    @Override
    public PaymentService create(PaymentRepository paymentRepository, TimeProvider timeProvider) {
        return new PaymentProcessor(PAYMENT_ID_GENERATOR, PAYMENT_FEE_CALCULATOR, paymentRepository , timeProvider);
    }
}
