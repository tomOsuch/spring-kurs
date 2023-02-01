package pl.springkurs.shop.payments.domain;

import lombok.RequiredArgsConstructor;
import pl.springkurs.shop.commons.Page;
import pl.springkurs.shop.commons.aop.Atomic;
import pl.springkurs.shop.commons.ResultPage;
import pl.springkurs.shop.payments.ports.PaymentService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class PaymentServiceDecorator implements PaymentService {

    private final PaymentService paymentService;

    @Atomic
    @Override
    public Payment process(PaymentRequest paymentRequest) {
        return paymentService.process(paymentRequest);
    }

    @Atomic
    public List<Payment> processes(List<PaymentRequest> paymentRequests) {
        return paymentRequests.stream().map(this::process).collect(Collectors.toList());
    }

    @Override
    public Payment getById(String id) {
        return paymentService.getById(id);
    }

    @Override
    public ResultPage<Payment> getByStatus(PaymentStatus status, Page page) {
        return paymentService.getByStatus(status, page);
    }
}
