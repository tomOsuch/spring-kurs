package pl.springkurs.shop.payments.adapters.rest;

import lombok.Data;
import pl.springkurs.shop.commons.Base;
import pl.springkurs.shop.commons.money.Money;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class PaymentRequestDto {

    @Min(value = 1, groups = {Base.class, Exception.class})
    private Long requestId;
    @Money(minValue = 10, group = Exception.class)
    @Pattern(regexp = "\\d+ [A-Z]+", groups = Base.class)
    @NotNull(groups = Base.class)
    private String value;
}
