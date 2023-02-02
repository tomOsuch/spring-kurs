package pl.springkurs.shop.commons.web;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class RestExceptionResponseBuilder {

    private final MessageSource messageSource;

    public ResponseEntity<ExceptionDto> build(String description, HttpStatus status) {
        return ResponseEntity.status(status).body(new ExceptionDto(description));
    }

    public ResponseEntity<ExceptionDto> build(Exception exception, HttpStatus status, Locale locale) {
        var description = getDescription(exception, locale);
        return build(description, status);
    }

    protected String getKey(Exception exception) {
        return exception.getClass().getSimpleName();
    }

    protected String getDescription(Exception exception, Locale locale, String...parameter) {
        var key = exception.getClass().getSimpleName();
        String description;
        try {
            description = messageSource.getMessage(key, parameter, locale);
        } catch (NoSuchMessageException ex) {
            description = key;
        }
        return description;
    }
}
