package com.desarrollo.laboratorio.ms_book_payments.controller;

import com.desarrollo.laboratorio.ms_book_payments.model.dto.OrderDTO;
import com.desarrollo.laboratorio.ms_book_payments.model.dto.PaymentDTO;
import com.desarrollo.laboratorio.ms_book_payments.model.entities.Order;
import com.desarrollo.laboratorio.ms_book_payments.model.entities.Payment;
import com.desarrollo.laboratorio.ms_book_payments.repository.OrderRepository;
import com.desarrollo.laboratorio.ms_book_payments.repository.PaymentRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@RestController
public class TestController {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private OrderRepository orderRepository;


    @PostMapping("/payments")
    public void createPayment(@RequestBody PaymentDTO payment) {

        Payment paymentEntity = new Payment();
        paymentEntity.setAmount(payment.getAmount());
        paymentEntity.setUserEmail(payment.getUserEmail());
        paymentEntity = paymentRepository.save(paymentEntity);

        List<Order> orders = new ArrayList<>();
        for(OrderDTO orderDto: payment.getOrders()){
            Order order = new Order(orderDto.getBookId(), orderDto.getQuantity(), paymentEntity);
            orders.add(orderRepository.save(order));
        }


    }

    @GetMapping("/payments")
    public List<Payment> getPayments(){
        return paymentRepository.findAll();
    }

    @GetMapping("/orders")
    public List<Order> getOrders(){
        return orderRepository.findAll();
    }
}
