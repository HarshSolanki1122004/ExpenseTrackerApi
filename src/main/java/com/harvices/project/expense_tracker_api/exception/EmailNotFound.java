package com.harvices.project.expense_tracker_api.exception;

public class EmailNotFound extends RuntimeException {
    public EmailNotFound(String message) {
        super(message);
    }
}
