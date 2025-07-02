package com.harvices.project.expense_tracker_api.exception;

public class ExpenseDeleted extends RuntimeException {
    public ExpenseDeleted(String message) {
        super(message);
    }
}
