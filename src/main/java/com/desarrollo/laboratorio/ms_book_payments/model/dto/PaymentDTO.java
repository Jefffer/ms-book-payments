package com.desarrollo.laboratorio.ms_book_payments.model.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private String userEmail;
    private double amount;
    private List<OrderDTO> orders;

}
