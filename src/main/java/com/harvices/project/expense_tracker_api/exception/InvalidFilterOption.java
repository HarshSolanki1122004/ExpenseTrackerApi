package com.harvices.project.expense_tracker_api.exception;

public class InvalidFilterOption extends RuntimeException {
    public InvalidFilterOption(String message) {
        super(message);
    }
}
