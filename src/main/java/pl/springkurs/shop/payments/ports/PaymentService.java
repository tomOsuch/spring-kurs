package pl.springkurs.shop.payments.ports;

import pl.springkurs.shop.commons.Page;
import pl.springkurs.shop.commons.ResultPage;
import pl.springkurs.shop.payments.domain.Payment;
import pl.springkurs.shop.payments.domain.PaymentRequest;
import pl.springkurs.shop.payments.domain.PaymentStatus;

public interface PaymentService {

    Payment process(PaymentRequest paymentRequest);

    Payment getById(String id);

    ResultPage<Payment> getByStatus(PaymentStatus status, Page page);
}
