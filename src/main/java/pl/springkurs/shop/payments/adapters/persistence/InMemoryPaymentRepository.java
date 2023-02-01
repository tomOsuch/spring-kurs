package pl.springkurs.shop.payments.adapters.persistence;

import lombok.Setter;
import pl.springkurs.shop.commons.Page;
import pl.springkurs.shop.commons.ResultPage;
import pl.springkurs.shop.payments.domain.Payment;
import pl.springkurs.shop.payments.domain.PaymentStatus;
import pl.springkurs.shop.payments.ports.PaymentRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public ResultPage<Payment> getByStatus(PaymentStatus status, Page page) {
        var data = payments.values().stream()
                .filter(payment -> payment.getStatus().equals(status))
                .collect(Collectors.toList());

        var totalPage = (long) Math.ceil((double) data.size() / page.getSize());
        return new ResultPage<>(data, page.getNumber(), totalPage);
    }
}
