package pl.springkurs.shop.payments.domain;

import lombok.*;
import org.javamoney.moneta.FastMoney;

import java.time.Instant;

@Builder
@Value
public class Payment {

    private String id;
    private FastMoney value;
    private Instant timestamp;
    private PaymentStatus status;

}
