package pl.springkurs.shop.payments.adapters.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.springkurs.shop.commons.web.ExceptionDto;
import pl.springkurs.shop.commons.web.RestExceptionResponseBuilder;
import pl.springkurs.shop.payments.domain.PaymentNotFoundException;

import java.util.Locale;

@ControllerAdvice(basePackages = "pl.springkurs.shop.payments.adapters.rest")
@RequiredArgsConstructor
public class PaymentRestExceptionHandler {

    private final RestExceptionResponseBuilder responseBuilder;

    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<ExceptionDto> onPaymentNotFound(PaymentNotFoundException exception, Locale locale) {
        return responseBuilder.build(exception, HttpStatus.NOT_FOUND, locale);
    }
}
