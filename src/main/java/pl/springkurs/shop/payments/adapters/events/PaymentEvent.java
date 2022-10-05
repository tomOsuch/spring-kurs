package pl.springkurs.shop.payments.adapters.events;

import lombok.Value;
import pl.springkurs.shop.payments.domain.Payment;

@Value
public class PaymentEvent {

    Payment payment;
    PaymentEventType paymentEventType;

    enum PaymentEventType {

        CREATED, FAILED

    }
}
