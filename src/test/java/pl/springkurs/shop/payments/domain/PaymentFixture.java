package pl.springkurs.shop.payments.domain;

import org.javamoney.moneta.FastMoney;
import org.mockito.stubbing.Answer;
import pl.springkurs.shop.payments.adapters.persistence.jpa.PaymentEntity;
import pl.springkurs.shop.payments.adapters.rest.PaymentDto;

import java.math.BigDecimal;
import java.time.Instant;
import static pl.springkurs.shop.payments.domain.PaymentStatus.CONFIRMED;


public class PaymentFixture {

    public static final String PAYMENT_ID = "1";
    public static final FastMoney PAYMENT_REQUEST_VALUE = FastMoney.of(1_000, "PLN");
    public static final FastMoney PAYMENT_FEE_VALUE = FastMoney.of(100, "PLN");
    public static final FastMoney PAYMENT_VALUE = PAYMENT_REQUEST_VALUE.add(PAYMENT_FEE_VALUE);
    public static final Instant PAYMENT_TIMESTAMP = Instant.now();
    public static final String TEST_ID = "1";
    public static final Instant TEST_TIME = Instant.now();
    public static final PaymentStatus PAYMENT_STATUS = CONFIRMED;
    public static final FastMoney TEST_VALUE = FastMoney.of(1_000, "PLN");
    public static final PaymentFeeCalculator PAYMENT_FEE_CALCULATOR= new PercentagePaymentFeeCalculator(0.1);
    public static final FastMoney TEST_PAYMENT_VALUE = TEST_VALUE.add(PAYMENT_FEE_CALCULATOR.calculateFee(TEST_VALUE));
    public final static Payment TEST_PAYMENT = Payment.builder()
            .id(TEST_ID)
            .value(TEST_PAYMENT_VALUE)
            .status(CONFIRMED)
            .timestamp(TEST_TIME)
            .build();

    public static final Answer<PaymentDto> MAP_TO_DTO = (invocation) -> {
        var payment = invocation.getArgument(0, Payment.class);
        var dto = new PaymentDto();
        dto.setId(payment.getId());
        dto.setValue(payment.getValue().toString());
        return dto;
    };

    public static PaymentEntity createPaymentEntity() {
        var entity = new PaymentEntity();
        entity.setId(PAYMENT_ID);
        entity.setValue(BigDecimal.valueOf(PAYMENT_VALUE.getNumber().doubleValueExact()));
        entity.setCurrency(PAYMENT_VALUE.getCurrency().getCurrencyCode());
        entity.setTimestamp(PAYMENT_TIMESTAMP);
        entity.setStatus(PAYMENT_STATUS.name());
        return entity;
    }

}
