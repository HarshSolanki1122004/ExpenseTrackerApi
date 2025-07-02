package com.harvices.project.expense_tracker_api.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupRequest {
    @NotBlank(message = "Username should not be blank")
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password should not be blank")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
}
