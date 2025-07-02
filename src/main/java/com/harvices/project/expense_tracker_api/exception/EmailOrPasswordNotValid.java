package com.harvices.project.expense_tracker_api.exception;

public class EmailOrPasswordNotValid extends RuntimeException {
    public EmailOrPasswordNotValid(String message) {
        super(message);
    }
}
