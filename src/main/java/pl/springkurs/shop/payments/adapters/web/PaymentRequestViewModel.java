package pl.springkurs.shop.payments.adapters.web;

import lombok.Data;
import pl.springkurs.shop.commons.money.Money;

@Data
public class PaymentRequestViewModel {

    @Money
    private String value;
}
