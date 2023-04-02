package pl.springkurs.shop.payments.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.springkurs.shop.payments.ports.PaymentRepository;
import pl.springkurs.shop.payments.ports.TimeProvider;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static pl.springkurs.shop.payments.domain.PaymentFixture.*;

@ExtendWith(MockitoExtension.class)
class PaymentProcessorTest {

    private PaymentProcessor paymentProcessor;
    @Mock
    private PaymentRepository paymentRepository;

    @BeforeEach
    void beforeEach() {
        PaymentIdGenerator generator = () -> TEST_ID;
        TimeProvider timeProvider = () -> TEST_TIME;
        Mockito.when(paymentRepository.save(any(Payment.class))).then(returnsFirstArg());
        paymentProcessor = new PaymentProcessor(generator, PAYMENT_FEE_CALCULATOR, paymentRepository, timeProvider);
    }

    @Test
    void given_a_payment_request_when_process_then_returns_a_payment() {
        var payment = paymentProcessor.process(new PaymentRequest(1L, TEST_VALUE));
        assertEquals(TEST_PAYMENT, payment);
    }

    @Disabled("Move to integration tests")
    @Test
    void given_a_payment_request_when_process_then_the_payment_is_persisted() {
        var payment = paymentProcessor.process(new PaymentRequest(1L, TEST_VALUE));
        Mockito.verify(paymentRepository).save(payment);
    }

}