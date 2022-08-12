package pl.tomaszosuch.springkurs.shop.payments;

public interface PaymentRepository {
    Payment save(Payment payment);
}
