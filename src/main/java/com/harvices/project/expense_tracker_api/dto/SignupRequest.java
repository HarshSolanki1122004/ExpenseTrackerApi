package com.harvices.project.expense_tracker_api.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignupRequest {
    @NotBlank(message = "Username should not be blank")
    private String username;

    @Email(message = "Email should be valid")
    @NotBlank
    private String email;

    @NotBlank(message = "Password should not be blank")
    private String password;
}
