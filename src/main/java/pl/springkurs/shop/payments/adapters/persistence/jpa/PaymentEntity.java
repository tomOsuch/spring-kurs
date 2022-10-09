package pl.springkurs.shop.payments.adapters.persistence.jpa;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.javamoney.moneta.FastMoney;
import pl.springkurs.shop.payments.domain.PaymentStatus;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.Instant;

@Entity(name = "Payment")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class PaymentEntity {

    @Id
    private String id;
    private BigDecimal value;
    private String currency;
    private Instant timestamp;
    private String status;
}
