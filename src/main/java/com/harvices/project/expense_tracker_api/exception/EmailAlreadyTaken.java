package com.harvices.project.expense_tracker_api.exception;

public class EmailAlreadyTaken extends RuntimeException {
    public EmailAlreadyTaken(String message) {
        super(message);
    }
}
