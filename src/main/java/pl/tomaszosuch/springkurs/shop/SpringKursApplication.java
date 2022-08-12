package pl.tomaszosuch.springkurs.shop;

import lombok.extern.java.Log;
import org.javamoney.moneta.FastMoney;
import pl.tomaszosuch.springkurs.shop.payments.*;
import pl.tomaszosuch.springkurs.shop.time.SystemTimeProvider;

@Log
public class SpringKursApplication {

    private static final String DEFAULT_CURRENCY_CODE = "PLN";

    public static PaymentService paymentService() {
        var payment = new UuidPaymentIdGenerator();
        var paymentFeeCalculator = new PercentagePaymentFeeCalculator(0.01);
        var paymentRepository = new InMemoryPaymentRepository();
        var timeProvider = new SystemTimeProvider();
        var paymentProcesor = new PaymentProcessor(payment, paymentFeeCalculator, paymentRepository, timeProvider);

        return new ConsoleLoggerPaymentServiceProxy(paymentProcesor);
    }

    public static void main(String[] args) {
        var paymentService = paymentService();
        var paymentRequest = new PaymentRequest(1L, FastMoney.of(1_000, DEFAULT_CURRENCY_CODE));
        var payment = paymentService.process(paymentRequest);
        log.info(payment.toString());
    }

}
