package com.swappy.userservice.auth;

import com.swappy.userservice.Entity.Role;
import com.swappy.userservice.Entity.User;
import com.swappy.userservice.Exceptions.UserAlreadyExistsException;
import com.swappy.userservice.Exceptions.UserNotFoundException;
import com.swappy.userservice.Repo.UserRepo;
import com.swappy.userservice.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepo repo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

        // Check if the email already exists in the repository
        var existingUserOptional = repo.findByEmail(request.getEmail());
        if (existingUserOptional.isPresent()) {
            throw new UserAlreadyExistsException("Email already exists");
        }

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repo.save(user);
        var jwtToken = jwtService.generateToken(user);
        // could send expiration date too
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repo.findByEmail(request.getEmail())
                .orElseThrow(()-> new UserNotFoundException("No user were found with this email"));

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
