package pl.springkurs.shop.payments;

import lombok.Value;

@Value
public class PaymentEvent {

    Payment payment;
    PaymentEventType paymentEventType;

    enum PaymentEventType {

        CREATED, FAILED

    }
}
