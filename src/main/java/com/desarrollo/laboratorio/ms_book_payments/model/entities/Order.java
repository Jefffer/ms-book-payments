package com.desarrollo.laboratorio.ms_book_payments.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(
        name="orders"
)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private int bookId;

    private int quantity;

    @ManyToOne
    @JoinColumn(name ="payment_id")
    @JsonIgnore
    private Payment payment;

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Order(UUID id, int bookId, int quantity, Payment payment) {
        this.id = id;
        this.bookId = bookId;
        this.quantity = quantity;
        this.payment = payment;
    }

    public Order(int bookId, int quantity, Payment payment) {
        this.bookId = bookId;
        this.quantity = quantity;
        this.payment = payment;
    }

    public Order() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getQuantity() {
        return quantity;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
