package pl.springkurs.shop.payments.ports;

import pl.springkurs.shop.payments.domain.Payment;

import java.util.Optional;

public interface PaymentRepository {
    Payment save(Payment payment);

    Optional<Payment> getById(String id);
}
