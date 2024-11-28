package com.example.infraction.service;

import com.braintreegateway.*;
import com.example.infraction.Repository.PaymentRepository;
import com.example.infraction.Repository.ValidationRepository;
import com.example.infraction.model.Payment;
import com.example.infraction.model.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class PaymentService {





    private static BraintreeGateway gateway = new BraintreeGateway(
            Environment.SANDBOX,
            "fr8ww8xycwk23y7t",
            "f3tqsn9y7mzvnzt3",
            "8e03f71fb87e4fa227368ca82e2db55e"
    );

    public String processPayment(String nonce, BigDecimal amount) {



        TransactionRequest request = new TransactionRequest()
                .amount(amount)
                .paymentMethodNonce(nonce)
                .options()
                .submitForSettlement(true)
                .done();

        Result<Transaction> result = gateway.transaction().sale(request);



        if (result.isSuccess()) {


            return result.getTarget().getId();
        } else {
            return "Erreur de paiement: " + result.getMessage();
        }

    }

}
