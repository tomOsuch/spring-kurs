package pl.springkurs.shop.payments.adapters.events;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import pl.springkurs.shop.payments.domain.Payment;

import static pl.springkurs.shop.payments.adapters.events.PaymentEvent.PaymentEventType.CREATED;

@Aspect
@Service
@RequiredArgsConstructor
public class PaymentEventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    @AfterReturning(value = "@annotation(pl.springkurs.shop.payments.domain.LogPayment)", returning = "payment")
    public void onPayment(Payment payment) {
        eventPublisher.publishEvent(new PaymentEvent(payment, CREATED));
    }
}
