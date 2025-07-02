package com.harvices.project.expense_tracker_api.service;
import com.harvices.project.expense_tracker_api.exception.ExpenseNotFound;
import com.harvices.project.expense_tracker_api.exception.InvalidFilterOption;
import com.harvices.project.expense_tracker_api.exception.StartDateOrEndDateCannotBeNull;
import com.harvices.project.expense_tracker_api.exception.UnAuthorizedExpense;
import com.harvices.project.expense_tracker_api.model.Expense;
import com.harvices.project.expense_tracker_api.model.User;
import com.harvices.project.expense_tracker_api.model.enums.ExpenseCategory;
import com.harvices.project.expense_tracker_api.repository.ExpenseRepository;
import com.harvices.project.expense_tracker_api.security.AuthHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final AuthHelper authHelper;

    public void createExpense(Expense expense, User user) {
        expense.setUser(user);
        expenseRepository.save(expense);
    }

    public List<Expense> getAllExpense(User user) {
        return expenseRepository.findByUser(user);
    }

    public Expense getExpenseById(int id) {
        Expense expense = expenseRepository.findById(id).orElseThrow(
                () -> new ExpenseNotFound("Expense not found"));
        return expense;
    }

    public void updateExpense(int id, Expense updatedExpense, User user) {

        Expense currentExpense = expenseRepository.findById(id).orElseThrow(
                () -> new ExpenseNotFound("Expense not found"));

        if (!(currentExpense.getUser().getId() == user.getId())) {
            throw new UnAuthorizedExpense("This expense is not belong to you");
        }

        currentExpense.setTitle(updatedExpense.getTitle());
        currentExpense.setAmount(updatedExpense.getAmount());
        currentExpense.setCategory(updatedExpense.getCategory());
        currentExpense.setDate(updatedExpense.getDate());

        expenseRepository.save(currentExpense);
    }

    public void deleteExpense(int id, User user) {
        Expense currentExpense = expenseRepository.findById(id).orElseThrow(
                () -> new ExpenseNotFound("Expense not found"));
        if (currentExpense.getUser().getId() != user.getId()) {
            throw new UnAuthorizedExpense("This expense is not belong to you");
        }
        expenseRepository.delete(currentExpense);
    }

    public List<Expense> getExpenseByFilter(User user, String filter, LocalDate startDate, LocalDate endDate) {
        LocalDate now = LocalDate.now();
        switch (filter.toLowerCase()) {
            case "week" -> {
                return expenseRepository.findByUserAndDateBetween(user, now.minusWeeks(1), now);
            }
            case "month" -> {
                return expenseRepository.findByUserAndDateBetween(user, now.minusMonths(1), now);
            }
            case "3months" -> {
                return expenseRepository.findByUserAndDateBetween(user, now.minusMonths(3), now);
            }
            case "custom" -> {
                if (startDate == null || endDate == null) {
                    throw new StartDateOrEndDateCannotBeNull("Start date or End date cannot be null");
                }
                return expenseRepository.findByUserAndDateBetween(user, startDate, endDate);
            }
            default -> throw new InvalidFilterOption("Invalid filter option");
        }
    }
    public List<Expense> getExpenseByCategory(ExpenseCategory expenseCategory,User user){
        return expenseRepository.findByUserAndCategory(user,expenseCategory);
    }

}
