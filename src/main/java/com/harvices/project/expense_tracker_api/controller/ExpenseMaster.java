package com.harvices.project.expense_tracker_api.controller;
import com.harvices.project.expense_tracker_api.model.Expense;
import com.harvices.project.expense_tracker_api.model.User;
import com.harvices.project.expense_tracker_api.model.enums.ExpenseCategory;
import com.harvices.project.expense_tracker_api.security.AuthHelper;
import com.harvices.project.expense_tracker_api.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("expense/api")
@AllArgsConstructor
public class ExpenseMaster {

    private final ExpenseService expenseService;
    private final AuthHelper authHelper;

    @PostMapping("/createExpense")
    public ResponseEntity<String> createExpense(@RequestBody @Valid Expense expense){
        User user = authHelper.getCurrentUser();
        expenseService.createExpense(expense,user);
        return ResponseEntity.ok("Expense Added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateExpense(@PathVariable int id,@RequestBody @Valid Expense expense){
        User user = authHelper.getCurrentUser();
        expenseService.updateExpense(id,expense,user);
        return ResponseEntity.ok("Expense Updated");
    }

    @GetMapping("/allExpense")
    public ResponseEntity<List<Expense>> getAllExpense(){
        User user = authHelper.getCurrentUser();
        List<Expense> allExpenses = expenseService.getAllExpense(user);
        return ResponseEntity.ok(allExpenses);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable int id){
        User user = authHelper.getCurrentUser();
        expenseService.deleteExpense(id,user);
        return ResponseEntity.ok("Expense Removed");
    }
    @GetMapping("/filter")
    public ResponseEntity<List<Expense>> filterExpenses(@RequestParam String filter,
                                                        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate startDate,
                                                        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate endDate)
    {
            User user = authHelper.getCurrentUser();
            List<Expense> expenses = expenseService.getExpenseByFilter(user,filter,startDate,endDate);
            return ResponseEntity.ok(expenses);
    }
    @GetMapping("/filterByCategory")
    public ResponseEntity<List<Expense>> filterExpenseByCategory(@RequestParam ExpenseCategory expenseCategory){
        User user = authHelper.getCurrentUser();
        List<Expense> expenses = expenseService.getExpenseByCategory(expenseCategory,user);
        return ResponseEntity.ok(expenses);
    }
}
