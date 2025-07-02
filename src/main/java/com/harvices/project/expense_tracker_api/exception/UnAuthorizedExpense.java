package com.harvices.project.expense_tracker_api.exception;

public class UnAuthorizedExpense extends RuntimeException {
    public UnAuthorizedExpense(String message) {
        super(message);
    }
}
