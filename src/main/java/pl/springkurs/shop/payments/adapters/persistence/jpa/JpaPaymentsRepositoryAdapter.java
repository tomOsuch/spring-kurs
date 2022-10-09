package pl.springkurs.shop.payments.adapters.persistence.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.springkurs.shop.payments.domain.Payment;
import pl.springkurs.shop.payments.ports.PaymentRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Component
@RequiredArgsConstructor
public class JpaPaymentsRepositoryAdapter implements PaymentRepository {

    private final JpaPaymentRepository paymentRepository;
    private final JpaPersistencePaymentMapper paymentMapper;
    @Override
    public Payment save(Payment payment) {
        var entity = paymentMapper.toEntity(payment);
        paymentRepository.save(entity);
        return paymentMapper.toDomain(entity);
    }

    @Override
    public Optional<Payment> getById(String id) {
        return paymentRepository.getById(id)
                .map(paymentMapper::toDomain);
    }
}
