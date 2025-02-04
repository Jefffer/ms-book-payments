package com.desarrollo.laboratorio.ms_book_payments.repository;

import com.desarrollo.laboratorio.ms_book_payments.model.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
