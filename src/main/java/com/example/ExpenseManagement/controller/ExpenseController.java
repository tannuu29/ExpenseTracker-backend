package com.example.ExpenseManagement.controller;

import com.example.ExpenseManagement.dto.ExpenseReqDto;
import com.example.ExpenseManagement.dto.ExpenseResDto;
import com.example.ExpenseManagement.enums.PaymentMode;
import com.example.ExpenseManagement.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/totalExpenses")
    public ResponseEntity<Double> totalExpense(){
        return ResponseEntity.ok(expenseService.totalExpense());
    }

    @GetMapping("/allExpense")
    public ResponseEntity<List<ExpenseResDto>> getAllExp(){
        return ResponseEntity.ok(expenseService.getAllExp());
    }
    @PostMapping("/addExpense")
    public ResponseEntity<String> addExpense(@RequestBody ExpenseReqDto expenseReqDto){
        return ResponseEntity.ok(expenseService.addExpense(expenseReqDto));
    }

    @GetMapping("/paymentMode")
    public ResponseEntity<List<ExpenseResDto>> getExpenseByPaymentMode(@RequestParam String paymentMode){
        return ResponseEntity.ok(expenseService.getExpByPaymentMode(paymentMode));
    }

    @GetMapping("/amountFilter")
    public ResponseEntity<List<ExpenseResDto>> getExpByAmount(@RequestParam Double minAmount, @RequestParam Double maxAmount){
        return ResponseEntity.ok(expenseService.getExpByAmount(minAmount, maxAmount));
    }

    @GetMapping("/dateFilter")
    public ResponseEntity<List<ExpenseResDto>> getExpByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to){
        return ResponseEntity.ok(expenseService.getExpByDate(from,to));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateExpense(@PathVariable Long id, @RequestBody ExpenseReqDto expenseReqDto){
        return new ResponseEntity<>(expenseService.updateExpense(id, expenseReqDto), HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long id){
        expenseService.deleteById(id);
        return ResponseEntity.ok("Deleted successfully");
    }

}
