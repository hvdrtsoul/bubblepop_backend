package com.paradiseco.bubblepop.controller;


import com.paradiseco.bubblepop.auth.AuthenticationRequest;
import com.paradiseco.bubblepop.auth.AuthenticationResponse;
import com.paradiseco.bubblepop.auth.RegisterRequest;
import com.paradiseco.bubblepop.entity.Role;
import com.paradiseco.bubblepop.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/registerAdmin")
    @Secured("ADMIN")
    public ResponseEntity<AuthenticationResponse> registerAdmin(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authService.register(request, Role.ADMIN));
    }

    @PostMapping("/registerBarista")
    @Secured("ADMIN")
    public ResponseEntity<AuthenticationResponse> registerBarista(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authService.register(request, Role.BARISTA));
    }


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(authService.login(request));
    }
}
