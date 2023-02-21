package pl.springkurs.shop.payments.adapters.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.springkurs.shop.commons.Extended;
import pl.springkurs.shop.commons.Page;
import pl.springkurs.shop.commons.web.LocationUri;
import pl.springkurs.shop.commons.web.ResultPageDto;
import pl.springkurs.shop.payments.ports.PaymentService;

import static pl.springkurs.shop.payments.domain.PaymentStatus.CONFIRMED;

@RequestMapping("api/payments")
@RestController
@RequiredArgsConstructor
public class PaymentRestController {

    private final PaymentService paymentService;
    private final RestPaymentMapper paymentMapper;

    @PostMapping
    public ResponseEntity<PaymentDto> process(/*@Valid*/ @Validated(Extended.class) @RequestBody PaymentRequestDto paymentRequestDto) {

        var paymentRequest = paymentMapper.toDomain(paymentRequestDto);
        var payment = paymentService.process(paymentRequest);
        var paymentDto = paymentMapper.toDto(payment);
        var locationUri = LocationUri.withId(paymentDto.getId());
        return ResponseEntity.created(locationUri).body(paymentDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<PaymentDto> findById(@PathVariable String id) {
        var payment = paymentService.getById(id);
        var paymentDto = paymentMapper.toDto(payment);
        return ResponseEntity.ok(paymentDto);
    }

    @GetMapping("confirmed")
    public ResponseEntity<ResultPageDto<PaymentDto>> getConfirmedPayments() {
        var page = new Page(0, 5);
        var resultPage = paymentService.getByStatus(CONFIRMED, page);
        var resultPageDto = paymentMapper.toDto(resultPage);
        return ResponseEntity.ok(resultPageDto);
    }

    /*@ExceptionHandler(PaymentNotFoundException.class)
    apublic ResponseEntity<ExceptionDto> onPaymentNotFound(PaymentNotFoundException payment) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .body(new ExceptionDto("Payment not found"));
    }*/

}
