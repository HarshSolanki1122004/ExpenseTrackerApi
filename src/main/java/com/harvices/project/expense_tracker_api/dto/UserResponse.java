package com.harvices.project.expense_tracker_api.dto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
    private int id;
    private String username;
    private String email;
    private String token;
}
