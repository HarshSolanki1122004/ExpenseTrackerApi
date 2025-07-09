package com.harvices.project.expense_tracker_api.security;
import com.harvices.project.expense_tracker_api.exception.EmailNotFound;
import com.harvices.project.expense_tracker_api.model.User;
import com.harvices.project.expense_tracker_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws EmailNotFound {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new EmailNotFound("Email not found"));
        return new UserDetailsImpl(user);
    }
}
