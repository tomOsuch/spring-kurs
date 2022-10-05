package pl.springkurs.shop.payments.adapters.persistence;

import lombok.Setter;
import pl.springkurs.shop.payments.domain.Payment;
import pl.springkurs.shop.payments.ports.PaymentRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryPaymentRepository implements PaymentRepository {

    @Setter
    private Map<String, Payment> payments = new HashMap<>();

    @Override
    public Payment save(Payment payment) {
        payments.put(payment.getId(), payment);
        return payment;
    }

    @Override
    public Optional<Payment> getById(String id) {
        return Optional.ofNullable(payments.get(id));
    }
}
