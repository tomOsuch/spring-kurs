package pl.springkurs.shop.payments.adapters.persistence.jpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;
import static pl.springkurs.shop.payments.domain.PaymentStatus.CONFIRMED;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class JpaPaymentRepositoryTest {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private JpaPaymentRepository paymentRepository;
    private static PaymentEntity createPaymentEntity() {
        var entity = new PaymentEntity();
        entity.setId("1");
        entity.setStatus(CONFIRMED.name());
        return entity;
    }

    @BeforeEach
    void beforeEach() {
        entityManager.persist(createPaymentEntity());
        entityManager.flush();
    }

    @Test
    void given_confirmed_payment_in_dataBase_when_get_by_status_then_returns_payment() {
        var result = paymentRepository.getByStatus(CONFIRMED.name(), PageRequest.of(0, 1));
        assertTrue(result.getContent().contains(createPaymentEntity()));
        assertEquals(1, result.getContent().size());
    }

}