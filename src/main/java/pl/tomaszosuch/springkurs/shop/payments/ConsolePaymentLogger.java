package pl.tomaszosuch.springkurs.shop.payments;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Order(100)
@Aspect
@Log
@RequiredArgsConstructor
public class ConsolePaymentLogger {

    @Pointcut("execution(* pl.tomaszosuch.springkurs.shop.payments.PaymentProcessor.proc*( ..))")
    //@Pointcut("@annotation(Loggable)")
    //@Pointcut("bean(paymentService)")
    void process() {

    }

    @Before(value = "process() && args(paymentRequest)")
    public void onStart(JoinPoint joinPoint, PaymentRequest paymentRequest) {
        // joinPoint.getSignature();
        log.info("New payment request: " + paymentRequest);
    }

    @AfterReturning(value = "process()", returning = "payment")
    public void onSuccess(Payment payment) {
        log.info("A new payment of %s has been created".formatted(payment.getValue()));
    }

    @AfterThrowing(value = "process()", throwing = "exception")
    public void onFailure(JoinPoint joinPoint, RuntimeException exception) {
        log.info("Payment processing failed %s (method: %s)".formatted(exception.getClass().getSimpleName(), joinPoint.getSignature().getName()));
    }

    @After("bean(paymentService)")
    public void onFinish() {
        log.info("Payment processing complete");
    }

    @PostConstruct
    public void init() {
        log.info("Initializing payment logger");
    }

    @PreDestroy
    public void destroy() {
        log.info("Destroying payment logger");
    }
}
