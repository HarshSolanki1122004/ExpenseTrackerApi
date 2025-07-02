package com.harvices.project.expense_tracker_api.controller;
import com.harvices.project.expense_tracker_api.dto.LoginRequest;
import com.harvices.project.expense_tracker_api.dto.SignupRequest;
import com.harvices.project.expense_tracker_api.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("expense/api")
public class UserMaster {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@RequestBody @Valid SignupRequest signupRequest){
        userService.registerUser(signupRequest);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody @Valid LoginRequest loginRequest){
        String token = userService.loginUser(loginRequest);
        return ResponseEntity.ok(token);
    }

}
