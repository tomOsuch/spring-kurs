package pl.springkurs.shop.payments;

import lombok.*;
import org.javamoney.moneta.FastMoney;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Builder
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    private String id;
    private FastMoney value;
    private Instant timestamp;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

}
