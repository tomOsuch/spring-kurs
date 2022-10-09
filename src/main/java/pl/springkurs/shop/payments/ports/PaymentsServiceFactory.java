package pl.springkurs.shop.payments.ports;

public interface PaymentsServiceFactory {

    PaymentService create(PaymentRepository paymentRepository, TimeProvider timeProvider);
}
