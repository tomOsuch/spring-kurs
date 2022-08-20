package pl.tomaszosuch.springkurs.shop.payments;

import pl.tomaszosuch.springkurs.shop.commons.aop.Length;

public interface PaymentService {

    Payment process(PaymentRequest paymentRequest);

    Payment getById(@Length String id);
}
