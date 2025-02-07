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
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookId;

    private int quantity;

    @ManyToOne
    @JoinColumn(name ="payment_id")
    @JsonIgnore
    private Payment payment;


    public Order(Long bookId, int quantity, Payment payment) {
        this.bookId = bookId;
        this.quantity = quantity;
        this.payment = payment;
    }

}
