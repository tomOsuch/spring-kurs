package pl.tomaszosuch.springkurs.shop.payments;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import static pl.tomaszosuch.springkurs.shop.payments.PaymentEvent.PaymentEventType.CREATED;

@Aspect
@Service
@RequiredArgsConstructor
public class PaymentEventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    @AfterReturning(value = "@annotation(LogPayment)", returning = "payment")
    public void onPayment(Payment payment) {
        eventPublisher.publishEvent(new PaymentEvent(payment, CREATED));
    }
}
