package pl.springkurs.shop.payments.ports;

import pl.springkurs.shop.commons.aop.Length;
import pl.springkurs.shop.payments.domain.Payment;
import pl.springkurs.shop.payments.domain.PaymentRequest;

public interface PaymentService {

    Payment process(PaymentRequest paymentRequest);

    Payment getById(@Length String id);
}
