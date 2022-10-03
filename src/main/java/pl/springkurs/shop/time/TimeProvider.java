package pl.springkurs.shop.time;

import java.time.Instant;

public interface TimeProvider {

    Instant getTimestamp();
}
