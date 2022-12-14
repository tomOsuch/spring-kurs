package pl.springkurs.shop.payments.domain;

import lombok.Value;
import org.javamoney.moneta.FastMoney;

@Value
public class PaymentRequest {

    Long id;
    FastMoney value;
}
