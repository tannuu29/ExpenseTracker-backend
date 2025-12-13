package com.example.ExpenseManagement.service;

import com.example.ExpenseManagement.dto.ExpenseReqDto;
import com.example.ExpenseManagement.dto.ExpenseResDto;
import com.example.ExpenseManagement.entity.Expense;
import com.example.ExpenseManagement.enums.PaymentMode;
import com.example.ExpenseManagement.repository.ExpenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepo expRepo;

    public String addExpense(ExpenseReqDto expenseReqDto){
        Expense expense = new Expense();
        expense.setDescription(expenseReqDto.getDescription());
        expense.setAmount(expenseReqDto.getAmount());
        expense.setPaymentMode(expenseReqDto.getPaymentMode());

        if (expenseReqDto.getDate() != null) {
            expense.setDate(expenseReqDto.getDate());
        } else {
            expense.setDate(LocalDate.now());  // fallback if no date sent
        }

        expRepo.save(expense);
        return "Expense successfully added!";
    }

    public List<ExpenseResDto> getAllExp(){
        List<Expense> expenses = expRepo.findAll();
        List<ExpenseResDto> dtos = new ArrayList<>();
        for (Expense expense : expenses){
            ExpenseResDto expenseResDto = new ExpenseResDto();
            expenseResDto.setId(expense.getId());
            expenseResDto.setDescription(expense.getDescription());
            expenseResDto.setAmount(expense.getAmount());
            expenseResDto.setPaymentMode(expense.getPaymentMode());
            expenseResDto.setDate(expense.getDate());
            dtos.add(expenseResDto);
        }
        return dtos;
    }
    public List<ExpenseResDto> getExpByPaymentMode(String paymentMode){
        List<Expense> expenses = expRepo.findByPaymentMode(PaymentMode.valueOf(paymentMode.toUpperCase()));
        List<ExpenseResDto> expenseResDtos = new ArrayList<>();
        for(Expense expense : expenses) {
            ExpenseResDto expenseResDto = new ExpenseResDto();
            expenseResDto.setId(expense.getId());
            expenseResDto.setDescription(expense.getDescription());
            expenseResDto.setAmount(expense.getAmount());
            expenseResDto.setPaymentMode(expense.getPaymentMode());
            expenseResDto.setDate(expense.getDate());
            expenseResDtos.add(expenseResDto);
        }
        return expenseResDtos;
    }

    public ExpenseResDto getExpByName(String description){
        Expense expense = expRepo.findByDescription(description).orElse(null);
        ExpenseResDto expenseResDto = new ExpenseResDto();
        expenseResDto.setDescription(expense.getDescription());
        expenseResDto.setAmount(expense.getAmount());
        expenseResDto.setPaymentMode(expense.getPaymentMode());
        expenseResDto.setDate(expense.getDate());
        return expenseResDto;
    }

    public Double totalExpense(){
        List<Expense> expenses = expRepo.findAll();
        Double totalExp = 0.0;
        for(Expense expense : expenses){
            totalExp +=expense.getAmount();
        }
        return totalExp;
    }

    public List<ExpenseResDto> getExpByAmount(Double minAmount, Double maxAmount){
        List<Expense> expenses = expRepo.findByAmountBetween(minAmount,maxAmount);
        List<ExpenseResDto> dtos = new ArrayList<>();
        for(Expense expense : expenses){
            ExpenseResDto expenseResDto = new ExpenseResDto();
            expenseResDto.setId(expense.getId());
            expenseResDto.setDescription(expense.getDescription());
            expenseResDto.setAmount(expense.getAmount());
            expenseResDto.setPaymentMode(expense.getPaymentMode());
            expenseResDto.setDate(expense.getDate());
            dtos.add(expenseResDto);
        }
        return dtos;
    }

    public List<ExpenseResDto> getExpByDate(LocalDate from, LocalDate to){
        List<Expense> expenses = expRepo.findByDateBetween(from,to);
        List<ExpenseResDto> dtos = new ArrayList<>();
        for(Expense expense : expenses){
            ExpenseResDto expenseResDto = new ExpenseResDto();
            expenseResDto.setId(expense.getId());
            expenseResDto.setDescription(expense.getDescription());
            expenseResDto.setAmount(expense.getAmount());
            expenseResDto.setPaymentMode(expense.getPaymentMode());
            expenseResDto.setDate(expense.getDate());
            dtos.add(expenseResDto);
        }
        return dtos;
    }

    public String updateExpense(Long id, ExpenseReqDto expenseReqDto){
        Expense expense = expRepo.findById(id).orElse(null);
        expense.setId(id);
        expense.setDescription(expenseReqDto.getDescription());
        expense.setAmount(expenseReqDto.getAmount());
        expense.setPaymentMode(expenseReqDto.getPaymentMode());
        expense.setDate(expenseReqDto.getDate());
        expense = expRepo.save(expense);
        return "Expense is successfully updated!";
    }

//    public String deleteExp(Long id){
//        Expense expense = expRepo.findById(id).orElse(null);
//        expRepo.deleteById(id);
//        return "Expense " + expense.getDescription() + " has been successfully deleted!";
//    }
    public void deleteById(Long id){
        expRepo.deleteById(id);
    }
}
