package pl.springkurs.shop.payments.domain;

import pl.springkurs.shop.payments.domain.PaymentIdGenerator;

import java.util.UUID;

public class UuidPaymentIdGenerator implements PaymentIdGenerator {

    @Override
    public String getNext() {
        return UUID.randomUUID().toString();
    }
}
