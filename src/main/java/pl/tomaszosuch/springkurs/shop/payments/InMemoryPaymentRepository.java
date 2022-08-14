package pl.tomaszosuch.springkurs.shop.payments;

import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryPaymentRepository implements PaymentRepository {

    @Setter
    private Map<String, Payment> payments = new HashMap<>();

    @Override
    public Payment save(Payment payment) {
        payments.put(payment.getId(), payment);
        return payment;
    }
}
