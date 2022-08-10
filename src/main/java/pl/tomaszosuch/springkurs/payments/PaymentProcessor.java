package pl.tomaszosuch.springkurs.payments;

import lombok.extern.java.Log;
import org.javamoney.moneta.FastMoney;

import java.time.Instant;

@Log
public class PaymentProcessor {

    private static final String LOG_FORMAT = "A new payment of %s has been initiated";

    private final PaymentIdGenerator paymentIdGenerator = new PaymentIdGenerator();
    private final PaymentFeeCalculator paymentFeeCalculator = new PaymentFeeCalculator(0.01);
    private final PaymentRepository paymentsRepository = new PaymentRepository();

    public Payment process(PaymentRequest paymentRequest) {
        var paymentValue = calculatePaymentValue(paymentRequest.getValue());
        var payment = createPayment(paymentValue);
        log.info(String.format(LOG_FORMAT, payment.getValue()));
        return paymentsRepository.save(payment);
    }

    private Payment createPayment(FastMoney paymentValue) {
        return Payment.builder()
                .id(paymentIdGenerator.getNext())
                .value(paymentValue)
                .timestamp(Instant.now())
                .status(PaymentStatus.STARTED)
                .build();
    }

    private FastMoney calculatePaymentValue(FastMoney paymentValue) {
        return paymentValue.add(paymentFeeCalculator.calculateFee(paymentValue));
    }
}
