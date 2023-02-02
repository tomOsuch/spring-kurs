package pl.springkurs.shop.payments.adapters.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.springkurs.shop.commons.web.ExceptionDto;
import pl.springkurs.shop.commons.web.RestExceptionHandler;
import pl.springkurs.shop.payments.domain.PaymentNotFoundException;

import java.util.Locale;

@ControllerAdvice(basePackages = "pl.springkurs.shop.payments.adapters.rest")
public class PaymentRestExceptionHandler extends RestExceptionHandler {

    public PaymentRestExceptionHandler(MessageSource messageSource) {
        super(messageSource);
    }

    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<ExceptionDto> onPaymentNotFound(PaymentNotFoundException exception, Locale locale) {
        return createResponse(exception, HttpStatus.NOT_FOUND, locale);
    }
}
