package com.harvices.project.expense_tracker_api.service;
import com.harvices.project.expense_tracker_api.exception.*;
import com.harvices.project.expense_tracker_api.model.Expense;
import com.harvices.project.expense_tracker_api.model.User;
import com.harvices.project.expense_tracker_api.model.enums.ExpenseCategory;
import com.harvices.project.expense_tracker_api.repository.ExpenseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public Expense createExpense(Expense expense, User user) {
        if(expense.isDeleted()){
            throw new ExpenseDeleted("Expense Is Deleted");
        }
        expense.setUser(user);
        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpense(User user) {
        return expenseRepository.findByUserAndDeletedFalse(user);
    }

    public Expense updateExpense(int id, Expense updatedExpense, User user) {
        if(updatedExpense.isDeleted()){
            throw new ExpenseDeleted("Expense Is Deleted");
        }

        Expense currentExpense = expenseRepository.findById(id).orElseThrow(
                () -> new ExpenseNotFound("Expense not found"));

        if (!(currentExpense.getUser().getId() == user.getId())) {
            throw new UnAuthorizedExpense("This expense is not belong to you");
        }

        currentExpense.setTitle(updatedExpense.getTitle());
        currentExpense.setAmount(updatedExpense.getAmount());
        currentExpense.setCategory(updatedExpense.getCategory());
        currentExpense.setDate(updatedExpense.getDate());

        return expenseRepository.save(currentExpense);
    }

    public Expense deleteExpense(int id, User user) {
        Expense currentExpense = expenseRepository.findById(id).orElseThrow(
                () -> new ExpenseNotFound("Expense not found"));
        if (currentExpense.getUser().getId() != user.getId()) {
            throw new UnAuthorizedExpense("This expense is not belong to you");
        }
        currentExpense.setDeleted(true);
        return expenseRepository.save(currentExpense);
    }

    public List<Expense> getExpenseByFilter(User user, String filter, LocalDate startDate, LocalDate endDate) {
        LocalDate now = LocalDate.now();
        switch (filter.toLowerCase()) {
            case "week" -> {
                return expenseRepository.findByUserAndDateBetweenAndDeletedFalse(user, now.minusWeeks(1), now);
            }
            case "month" -> {
                return expenseRepository.findByUserAndDateBetweenAndDeletedFalse(user, now.minusMonths(1), now);
            }
            case "3months" -> {
                return expenseRepository.findByUserAndDateBetweenAndDeletedFalse(user, now.minusMonths(3), now);
            }
            case "custom" -> {
                if (startDate == null || endDate == null) {
                    throw new StartDateOrEndDateCannotBeNull("Start date or End date cannot be null");
                }
                return expenseRepository.findByUserAndDateBetweenAndDeletedFalse(user, startDate, endDate);
            }
            default -> throw new InvalidFilterOption("Invalid filter option");
        }
    }
    public List<Expense> getExpenseByCategory(ExpenseCategory expenseCategory,User user){
        return expenseRepository.findByUserAndCategoryAndDeletedFalse(user,expenseCategory);
    }

}
