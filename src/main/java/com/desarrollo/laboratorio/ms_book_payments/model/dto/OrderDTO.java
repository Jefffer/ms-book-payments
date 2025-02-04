package com.desarrollo.laboratorio.ms_book_payments.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


public class OrderDTO {
    private int bookId;
    private int quantity;

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

    public OrderDTO(int bookId, int quantity) {
        this.bookId = bookId;
        this.quantity = quantity;
    }
}
