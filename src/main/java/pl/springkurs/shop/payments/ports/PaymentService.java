package pl.springkurs.shop.payments.ports;

import pl.springkurs.shop.commons.Page;
import pl.springkurs.shop.commons.aop.Length;
import pl.springkurs.shop.commons.aop.ResultPage;
import pl.springkurs.shop.payments.domain.Payment;
import pl.springkurs.shop.payments.domain.PaymentRequest;
import pl.springkurs.shop.payments.domain.PaymentStatus;

public interface PaymentService {

    Payment process(PaymentRequest paymentRequest);

    Payment getById(@Length String id);

    ResultPage<Payment> getByStatus(PaymentStatus status, Page page);
}
