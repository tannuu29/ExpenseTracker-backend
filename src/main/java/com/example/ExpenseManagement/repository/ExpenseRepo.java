package com.example.ExpenseManagement.repository;

import com.example.ExpenseManagement.dto.ExpenseResDto;
import com.example.ExpenseManagement.entity.Expense;
import com.example.ExpenseManagement.enums.PaymentMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense, Long> {
    Optional<Expense> findByDescription(String description);
    List<Expense> findByPaymentMode(PaymentMode paymentMode);
    List<Expense> findByAmountBetween(Double minAmount, Double maxAmount);
    List<Expense> findByDateBetween(LocalDate from, LocalDate to);
}
