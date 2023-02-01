package pl.springkurs.shop.payments.adapters.rest;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ValueMapping;
import pl.springkurs.shop.commons.ResultPage;
import pl.springkurs.shop.commons.money.FastMoneyMapper;
import pl.springkurs.shop.commons.web.ResultPageDto;
import pl.springkurs.shop.payments.domain.Payment;
import pl.springkurs.shop.payments.domain.PaymentRequest;
import pl.springkurs.shop.payments.domain.PaymentStatus;

import java.util.List;

@Mapper(componentModel = "spring", uses = FastMoneyMapper.class)
public interface RestPaymentMapper {

    @Mapping(source = "requestId", target = "id")
    PaymentRequest toDomain(PaymentRequestDto dto);

    PaymentDto toDto(Payment payment);

    @ValueMapping(source = "STARTED", target = "NOT_CONFIRMED")
    @ValueMapping(source = "FAILED", target = "NOT_CONFIRMED")
    @ValueMapping(source = "CANCELED", target = "NOT_CONFIRMED")
    String toDto(PaymentStatus status);

    @IterableMapping(elementTargetType = PaymentDto.class)
    List<PaymentDto> toDto(List<Payment> payments);

    ResultPageDto<PaymentDto> toDto(ResultPage<Payment> resultPage);

}
