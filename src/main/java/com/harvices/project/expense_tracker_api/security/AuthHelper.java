package com.harvices.project.expense_tracker_api.security;
import com.harvices.project.expense_tracker_api.exception.EmailNotFound;
import com.harvices.project.expense_tracker_api.model.User;
import com.harvices.project.expense_tracker_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthHelper {
    private final UserRepository userRepository;
    public User getCurrentUser(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email).orElseThrow(
                () -> new EmailNotFound("Email not found")
        );
    }
}
