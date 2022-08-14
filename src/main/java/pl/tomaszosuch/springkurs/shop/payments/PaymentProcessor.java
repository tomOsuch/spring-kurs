package pl.tomaszosuch.springkurs.shop.payments;

import org.javamoney.moneta.FastMoney;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tomaszosuch.springkurs.shop.time.TimeProvider;

import java.time.Instant;

@Service
public class PaymentProcessor implements PaymentService {

    private static final PaymentStatus DEFAULT_PAYMENT_STATUS = PaymentStatus.STARTED;

    private final PaymentIdGenerator paymentIdGenerator;
    private final PaymentFeeCalculator paymentFeeCalculator;
    private final PaymentRepository paymentsRepository;
    private final TimeProvider timeProvider;

    @Autowired
    public PaymentProcessor(PaymentIdGenerator paymentIdGenerator, PaymentFeeCalculator paymentFeeCalculator, PaymentRepository paymentsRepository, TimeProvider timeProvider) {
        this.paymentIdGenerator = paymentIdGenerator;
        this.paymentFeeCalculator = paymentFeeCalculator;
        this.paymentsRepository = paymentsRepository;
        this.timeProvider = timeProvider;
    }

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
}
