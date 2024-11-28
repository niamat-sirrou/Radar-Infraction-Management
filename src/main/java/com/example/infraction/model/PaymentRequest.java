package com.example.infraction.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PaymentRequest {
    private String cardHolderName;
    private String cardNumber;
    private String cvc;
    private String expiryDate;
    private Double amount;
}
