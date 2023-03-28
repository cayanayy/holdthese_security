package com.cayanay.holdthesesecurity.business.abstracts;

import com.cayanay.holdthesesecurity.business.requests.AuthenticationRequest;
import com.cayanay.holdthesesecurity.business.requests.RegisterRequest;
import com.cayanay.holdthesesecurity.business.responses.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest registerRequest);

    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
