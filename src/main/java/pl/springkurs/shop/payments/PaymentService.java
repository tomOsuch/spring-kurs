package pl.springkurs.shop.payments;

import pl.springkurs.shop.commons.aop.Length;

public interface PaymentService {

    Payment process(PaymentRequest paymentRequest);

    Payment getById(@Length String id);
}
