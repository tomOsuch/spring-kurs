package pl.tomaszosuch.springkurs.payments;

import lombok.Builder;
import lombok.Value;
import org.javamoney.moneta.FastMoney;

import java.time.Instant;

@Builder
@Value
public class Payment {

    String id;
    FastMoney value;
    Instant timestamp;
    PaymentStatus status;

}
