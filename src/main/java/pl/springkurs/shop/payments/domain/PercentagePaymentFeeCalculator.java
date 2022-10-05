package pl.springkurs.shop.payments.domain;

import lombok.RequiredArgsConstructor;
import org.javamoney.moneta.FastMoney;
import pl.springkurs.shop.payments.domain.PaymentFeeCalculator;

@RequiredArgsConstructor
class PercentagePaymentFeeCalculator implements PaymentFeeCalculator {

    private final double percentage;

    @Override
    public FastMoney calculateFee(FastMoney paymentValue) {
        return paymentValue.multiply(percentage);
    }
}
