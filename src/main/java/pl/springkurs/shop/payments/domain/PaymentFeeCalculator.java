package pl.springkurs.shop.payments.domain;

import org.javamoney.moneta.FastMoney;

interface PaymentFeeCalculator {
    FastMoney calculateFee(FastMoney paymentValue);
}
