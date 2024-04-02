package com.swappy.userservice.Controller;

import com.swappy.userservice.auth.AuthenticationRequest;
import com.swappy.userservice.auth.AuthenticationResponse;
import com.swappy.userservice.auth.AuthenticationService;
import com.swappy.userservice.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    )
    {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest request
    )
    {
        return ResponseEntity.ok(authService.authenticate(request));
    }


}
