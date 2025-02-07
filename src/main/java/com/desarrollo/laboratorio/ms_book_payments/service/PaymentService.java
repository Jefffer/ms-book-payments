package com.desarrollo.laboratorio.ms_book_payments.service;

import com.desarrollo.laboratorio.ms_book_payments.model.dto.OrderDTO;
import com.desarrollo.laboratorio.ms_book_payments.model.dto.PaymentDTO;
import com.desarrollo.laboratorio.ms_book_payments.model.entities.Order;
import com.desarrollo.laboratorio.ms_book_payments.model.entities.Payment;
import com.desarrollo.laboratorio.ms_book_payments.repository.OrderRepository;
import com.desarrollo.laboratorio.ms_book_payments.repository.PaymentRepository;
import jakarta.ws.rs.NotFoundException;
import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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

    public ResponseEntity<Payment> createPayment(PaymentDTO paymentDto) {
        boolean existsAllBooks = booksFacade.booksAreValid(paymentDto.getOrders());

        if (!existsAllBooks)
            return ResponseEntity.notFound().build();

        Payment paymentEntity = new Payment();
        paymentEntity.setAmount(paymentDto.getAmount());
        paymentEntity.setUserEmail(paymentDto.getUserEmail());
        paymentEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        paymentEntity = paymentRepository.save(paymentEntity);
        paymentEntity.setOrders(new ArrayList<>());
        for(OrderDTO orderDto: paymentDto.getOrders()){
            Order order = new Order(orderDto.getBookId(), orderDto.getQuantity(), paymentEntity);
            order = orderRepository.save(order);

            paymentEntity.getOrders().add(order);
        }

        return ResponseEntity.ok(paymentEntity);

    }

    public void delete(Long id) {
        paymentRepository.deleteById(id);
    }
}
