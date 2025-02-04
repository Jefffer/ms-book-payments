package com.desarrollo.laboratorio.ms_book_payments.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(
        name="payments"
)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String userEmail;

    private Double amount;

    @OneToMany(mappedBy = "payment")
    private List<Order> orders;


    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> order) {
        this.orders = order;
    }

    public Payment() {

    }

    public Payment(UUID id, String userEmail, Double amount, List<Order> orders) {
        this.id = id;
        this.userEmail = userEmail;
        this.amount = amount;
        this.orders = orders;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail( String userEmail) {
        this.userEmail = userEmail;
    }


    public double getAmount() {
        return amount;
    }

    public void setAmount( double amount) {
        this.amount = amount;
    }


}
