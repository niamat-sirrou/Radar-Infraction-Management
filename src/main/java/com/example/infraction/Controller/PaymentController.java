package com.example.infraction.Controller;

import com.example.infraction.model.Infraction;
import com.example.infraction.requests.PaymentRequest;
import com.example.infraction.service.InfractionService;
import com.example.infraction.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;



    @Autowired
    InfractionService infractionService;



    @GetMapping("/cart/{id}")
    public String getCart(Model model, @PathVariable long id) {

        Optional<Infraction> infraction = infractionService.getInfraction(id);

        model.addAttribute("infraction", infraction.get());

        return "payment.html";
    }

    @PostMapping("/process-payment/{id}")
    @ResponseBody
    public String processPayment(@RequestBody PaymentRequest paymentRequest, @PathVariable long id) {
        String nonce = paymentRequest.getNonce();
        BigDecimal amount = paymentRequest.getAmount();
        String result = paymentService.processPayment(nonce, amount);

        //  paymentService.saveOrder(paymentRequest);

        return result;
    }

}
