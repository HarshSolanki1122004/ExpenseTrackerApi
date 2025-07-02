package com.harvices.project.expense_tracker_api.repository;
import com.harvices.project.expense_tracker_api.model.Expense;
import com.harvices.project.expense_tracker_api.model.User;
import com.harvices.project.expense_tracker_api.model.enums.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Integer> {
    List<Expense> findByUser(User user);
    List<Expense> findByUserAndDateBetween(User user, LocalDate startDate,LocalDate endDate);
    List<Expense> findByUserAndCategory(User user, ExpenseCategory expenseCategory);
}
