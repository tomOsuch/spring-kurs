package pl.springkurs.shop.payments.adapters.persistence.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface JpaPaymentRepository extends JpaRepository<PaymentEntity, String> {

    Page<PaymentEntity> getByStatus(String status, Pageable pageable);

    @Query("select p from Payment p where p.status = 'COMPLITED' and p.value >= :value")
    List<PaymentEntity> getCompletedWithValue(@Param("value") BigDecimal value);
}
