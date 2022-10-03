package pl.springkurs.shop.payments;

import org.javamoney.moneta.FastMoney;

public interface PaymentFeeCalculator {
    FastMoney calculateFee(FastMoney paymentValue);
}
