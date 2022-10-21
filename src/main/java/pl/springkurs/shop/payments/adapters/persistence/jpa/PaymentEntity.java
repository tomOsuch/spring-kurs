package pl.springkurs.shop.payments.adapters.persistence.jpa;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.NamedQuery;
import java.math.BigDecimal;
import java.time.Instant;

@NamedQuery(name = PaymentEntity.GET_BY_STATUS, query = "SELECT p FROM Payment p WHERE p.status = :status")
@NamedQuery(name = PaymentEntity.COUNT_BY_STATUS, query = "SELECT  COUNT (p) FROM Payment  p WHERE p.status =: status")
@Entity(name = "Payment")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class PaymentEntity {

    public static final String GET_BY_STATUS = "paymentByStatus";
    public static final String COUNT_BY_STATUS = "paymentCOUNTByStatus";

    @Id
    private String id;
    private BigDecimal value;
    private String currency;
    private Instant timestamp;
    private String status;
}
