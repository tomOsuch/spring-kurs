package pl.springkurs.shop.payments.adapters.web;

import org.mapstruct.Mapper;
import pl.springkurs.shop.commons.money.FastMoneyMapper;
import pl.springkurs.shop.payments.domain.Payment;
import pl.springkurs.shop.payments.domain.PaymentRequest;

@Mapper(componentModel = "spring", uses = FastMoneyMapper.class)
public interface WebPaymentMapper {

    PaymentRequest toDomain(PaymentRequestViewModel paymentRequestViewModel);

    PaymentViewModel toViewModel(Payment payment);
}
