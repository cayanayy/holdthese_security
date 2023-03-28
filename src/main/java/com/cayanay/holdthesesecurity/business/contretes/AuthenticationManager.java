package com.cayanay.holdthesesecurity.business.contretes;

import com.cayanay.holdthesesecurity.business.abstracts.AuthenticationService;
import com.cayanay.holdthesesecurity.business.requests.AuthenticationRequest;
import com.cayanay.holdthesesecurity.business.requests.RegisterRequest;
import com.cayanay.holdthesesecurity.business.responses.AuthenticationResponse;
import com.cayanay.holdthesesecurity.core.utilities.mappers.ModelMapperManager;
import com.cayanay.holdthesesecurity.dataacces.UserRepository;
import com.cayanay.holdthesesecurity.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationManager implements AuthenticationService {
    private final UserRepository userRepository;
    private final ModelMapperManager modelMapperManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtManager jwtManager;
    private final org.springframework.security.authentication.AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        User user = modelMapperManager.forRequest().map(registerRequest, User.class);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userRepository.save(user);
        var jwtToken = jwtManager.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        var user = userRepository.findByUsername(authenticationRequest.getUsername()).orElseThrow();
        var jwtToken = jwtManager.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
