package com.desarrollo.laboratorio.ms_book_payments.model.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long bookId;
    private int quantity;

}
