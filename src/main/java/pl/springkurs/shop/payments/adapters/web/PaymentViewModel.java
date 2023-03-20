package pl.springkurs.shop.payments.adapters.web;

import lombok.Data;

import java.io.Serializable;

@Data
public class PaymentViewModel implements Serializable {

    private String value;
    private String status;
}
