package pl.tomaszosuch.springkurs.payments;

import lombok.RequiredArgsConstructor;
import org.javamoney.moneta.FastMoney;

@RequiredArgsConstructor
public class PaymentFeeCalculator {

    private final double percentage;

    public FastMoney calculateFee(FastMoney paymentValue) {
        return paymentValue.multiply(percentage);
    }
}
