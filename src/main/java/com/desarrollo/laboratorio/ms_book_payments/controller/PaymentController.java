package com.desarrollo.laboratorio.ms_book_payments.controller;

import com.desarrollo.laboratorio.ms_book_payments.model.dto.OrderDTO;
import com.desarrollo.laboratorio.ms_book_payments.model.dto.PaymentDTO;
import com.desarrollo.laboratorio.ms_book_payments.model.entities.Order;
import com.desarrollo.laboratorio.ms_book_payments.model.entities.Payment;
import com.desarrollo.laboratorio.ms_book_payments.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping()
    public ResponseEntity<Payment> createPayment(@RequestBody PaymentDTO payment) {
        if(!isValid(payment))
            return null;
        return paymentService.createPayment(payment);


    }

    @GetMapping()
    public List<Payment> getPayments(){
        return paymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable Long id){
        if(id==null)
            return null;
        return paymentService.getPaymentById(id);
    }

    @DeleteMapping()
    public ResponseEntity<Void> deletePayment(@PathVariable Long id){
        if(id==null)
            return null;
        paymentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private boolean isValid(PaymentDTO paymentDTO){

        if (paymentDTO == null || paymentDTO.getOrders().isEmpty() || !isValidEmail(paymentDTO.getUserEmail()))
            return false;
        return true;
    }

    private static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
