package com.harvices.project.expense_tracker_api.service;
import com.harvices.project.expense_tracker_api.dto.LoginRequest;
import com.harvices.project.expense_tracker_api.dto.SignupRequest;
import com.harvices.project.expense_tracker_api.dto.UserResponse;
import com.harvices.project.expense_tracker_api.exception.EmailAlreadyTaken;
import com.harvices.project.expense_tracker_api.exception.EmailOrPasswordNotValid;
import com.harvices.project.expense_tracker_api.exception.UserNotFound;
import com.harvices.project.expense_tracker_api.model.User;
import com.harvices.project.expense_tracker_api.repository.UserRepository;
import com.harvices.project.expense_tracker_api.security.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public User registerUser(SignupRequest signupRequest){
        if(userRepository.findByEmail(signupRequest.getEmail()).isPresent()){
            throw new EmailAlreadyTaken("Email already in use");
        }
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setUsername(signupRequest.getUsername());
        user.setPassword(encoder.encode(signupRequest.getPassword()));
        return userRepository.save(user);
    }
    public UserResponse loginUser(LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword())
        );
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new UserNotFound("User not found"));
        String token = jwtUtils.generateToken(loginRequest.getEmail());

        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                token
        );
    }
}
