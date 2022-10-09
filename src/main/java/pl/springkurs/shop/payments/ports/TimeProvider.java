package pl.springkurs.shop.payments.ports;

import java.time.Instant;

public interface TimeProvider {

    Instant getTimestamp();
}
