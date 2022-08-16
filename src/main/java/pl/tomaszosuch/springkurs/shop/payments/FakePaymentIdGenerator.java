package pl.tomaszosuch.springkurs.shop.payments;


public class FakePaymentIdGenerator implements PaymentIdGenerator {

    private static final String ID = "1";
    @Override
    public String getNext() {
        return ID;
    }
}
