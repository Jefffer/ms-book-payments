package com.desarrollo.laboratorio.ms_book_payments.service;

import com.desarrollo.laboratorio.ms_book_payments.model.dto.OrderDTO;
import com.desarrollo.laboratorio.ms_book_payments.model.dto.PaymentDTO;
import com.desarrollo.laboratorio.ms_book_payments.model.entities.Order;
import com.desarrollo.laboratorio.ms_book_payments.model.entities.Payment;
import com.desarrollo.laboratorio.ms_book_payments.repository.OrderRepository;
import com.desarrollo.laboratorio.ms_book_payments.repository.PaymentRepository;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BooksFacade booksFacade;

    public List<Payment> getAllPayments(){
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long id){
        Optional<Payment> optPayment = paymentRepository.findById(id);
        if(!optPayment.isPresent())
            throw new NotFoundException("Payment not found");
        return optPayment.get();
    }

    public Payment createPayment(PaymentDTO paymentDto) {
        boolean existsAllBooks = booksFacade.booksAreValid(paymentDto.getOrders());
        if (!existsAllBooks)
            return null;

        Payment paymentEntity = new Payment();
        paymentEntity.setAmount(paymentDto.getAmount());
        paymentEntity.setUserEmail(paymentDto.getUserEmail());
        paymentEntity = paymentRepository.save(paymentEntity);

        for(OrderDTO orderDto: paymentDto.getOrders()){
            Order order = new Order(orderDto.getBookId(), orderDto.getQuantity(), paymentEntity);
            orderRepository.save(order);
        }

        return paymentEntity;

    }
}
