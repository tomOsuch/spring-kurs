package pl.tomaszosuch.springkurs.shop.payments;

public interface PaymentService {

    Payment process(PaymentRequest paymentRequest);
}
