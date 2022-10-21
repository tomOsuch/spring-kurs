package pl.springkurs.shop.payments.ports;

import pl.springkurs.shop.commons.Page;
import pl.springkurs.shop.commons.aop.ResultPage;
import pl.springkurs.shop.payments.domain.Payment;
import pl.springkurs.shop.payments.domain.PaymentStatus;

import java.util.Optional;

public interface PaymentRepository {
    Payment save(Payment payment);

    Optional<Payment> getById(String id);

    ResultPage<Payment> getByStatus(PaymentStatus status, Page page);
}
