package pl.tomaszosuch.springkurs.shop.payments;

import lombok.RequiredArgsConstructor;
import org.javamoney.moneta.FastMoney;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
public class PercentagePaymentFeeCalculator implements PaymentFeeCalculator {

    private final double percentage;

    @Override
    public FastMoney calculateFee(FastMoney paymentValue) {
        return paymentValue.multiply(percentage);
    }
}
