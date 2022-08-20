package pl.tomaszosuch.springkurs.shop.payments;

import java.util.Optional;

public interface PaymentRepository {
    Payment save(Payment payment);

    Optional<Payment> getById(String id);
}
