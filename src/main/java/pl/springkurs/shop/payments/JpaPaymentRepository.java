package pl.springkurs.shop.payments;

import lombok.Setter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public class JpaPaymentRepository implements PaymentRepository {

    @PersistenceContext
    @Setter
    private EntityManager entityManager;
    @Override
    public Payment save(Payment payment) {
        entityManager.persist(payment);
        return payment;
    }

    @Override
    public Optional<Payment> getById(String id) {
        return Optional.ofNullable(entityManager.find(Payment.class, id));
    }
}
