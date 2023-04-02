package pl.springkurs.shop.payments.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static pl.springkurs.commons.UuidMatcher.idUuid;

class UuidPaymentIdGeneratorTest {

    private final UuidPaymentIdGenerator idGenerator = new UuidPaymentIdGenerator();

    @Test
    void when_get_next_then_returns_unique_ids() {
        assertNotEquals(idGenerator.getNext(), idGenerator.getNext());
    }

    @Test
    void when_get_next_then_returns_valid_id() {
        assertThat(idGenerator.getNext(), idUuid());
    }

}