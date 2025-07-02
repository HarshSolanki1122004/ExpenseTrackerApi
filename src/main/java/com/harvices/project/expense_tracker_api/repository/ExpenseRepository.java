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
    List<Expense> findByUserAndDateBetweenAndDeletedFalse(User user, LocalDate start, LocalDate end);
    List<Expense> findByUserAndCategoryAndDeletedFalse(User user, ExpenseCategory category);
    List<Expense> findByUserAndDeletedFalse(User user);



}
