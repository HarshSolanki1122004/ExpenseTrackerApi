package com.harvices.project.expense_tracker_api.exception;

public class ExpenseNotFound extends RuntimeException {
    public ExpenseNotFound(String message) {
        super(message);
    }
}
