package com.cayanay.holdthesesecurity.controllers;

import com.cayanay.holdthesesecurity.business.contretes.AuthenticationManager;
import com.cayanay.holdthesesecurity.business.requests.AuthenticationRequest;
import com.cayanay.holdthesesecurity.business.requests.RegisterRequest;
import com.cayanay.holdthesesecurity.business.responses.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authenticationManager.register(registerRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationManager.authenticate(authenticationRequest));
    }
}
