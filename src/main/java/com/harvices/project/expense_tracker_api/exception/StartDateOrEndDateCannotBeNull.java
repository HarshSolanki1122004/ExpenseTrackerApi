package com.harvices.project.expense_tracker_api.exception;

public class StartDateOrEndDateCannotBeNull extends RuntimeException {
    public StartDateOrEndDateCannotBeNull(String message) {
        super(message);
    }
}
