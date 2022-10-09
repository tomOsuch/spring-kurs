package pl.springkurs.shop.payments.adapters.persistence.jpa;

import lombok.Setter;
import org.springframework.stereotype.Repository;
import pl.springkurs.shop.payments.domain.Payment;
import pl.springkurs.shop.payments.ports.PaymentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class JpaPaymentRepository {

    @PersistenceContext
    @Setter
    private EntityManager entityManager;
    public PaymentEntity save(PaymentEntity payment) {
        entityManager.persist(payment);
        return payment;
    }

    public Optional<PaymentEntity> getById(String id) {
        return Optional.ofNullable(entityManager.find(PaymentEntity.class, id));
    }
}
