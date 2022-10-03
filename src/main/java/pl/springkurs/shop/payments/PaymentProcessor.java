package pl.springkurs.shop.payments;

import lombok.RequiredArgsConstructor;
import org.javamoney.moneta.FastMoney;
import pl.springkurs.shop.commons.aop.Length;
import pl.springkurs.shop.commons.aop.Lock;
import pl.springkurs.shop.commons.aop.LogExecutionTime;
import pl.springkurs.shop.commons.aop.Retry;
import pl.springkurs.shop.time.TimeProvider;

@RequiredArgsConstructor
public class PaymentProcessor implements PaymentService {

    private static final PaymentStatus DEFAULT_PAYMENT_STATUS = PaymentStatus.STARTED;

    private final PaymentIdGenerator paymentIdGenerator;
    private final PaymentFeeCalculator paymentFeeCalculator;
    private final PaymentRepository paymentsRepository;
    private final TimeProvider timeProvider;

    @Lock
    @Retry(attempts = 2)
    @LogExecutionTime
    @LogPayment
    @Override
    public Payment process(PaymentRequest paymentRequest) {
        var paymentValue = calculatePaymentValue(paymentRequest.getValue());
        var payment = Payment.builder()
                .id(paymentIdGenerator.getNext())
                .value(paymentValue)
                .timestamp(timeProvider.getTimestamp())
                .status(DEFAULT_PAYMENT_STATUS)
                .build();
        return paymentsRepository.save(payment);
    }

    private FastMoney calculatePaymentValue(FastMoney paymentValue) {
        return paymentValue.add(paymentFeeCalculator.calculateFee(paymentValue));
    }

    @Override
    public Payment getById(@Length String id) {
        return paymentsRepository.getById(id)
                .orElseThrow(PaymentNotFoundException::new);
    }

}
