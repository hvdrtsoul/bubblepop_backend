package com.paradiseco.bubblepop.service;

import com.paradiseco.bubblepop.auth.AuthenticationRequest;
import com.paradiseco.bubblepop.auth.AuthenticationResponse;
import com.paradiseco.bubblepop.auth.RegisterRequest;
import com.paradiseco.bubblepop.config.JwtService;
import com.paradiseco.bubblepop.entity.Role;
import com.paradiseco.bubblepop.entity.UserEntity;
import com.paradiseco.bubblepop.exception.UserNotFoundException;
import com.paradiseco.bubblepop.repository.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request, Role role) {
        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(role);
        userRepo.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


    public AuthenticationResponse login(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        UserEntity user = userRepo.findByUsername(request.getUsername());
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .baristaId(user.getId())
                .token(jwtToken)
                .build();
    }
}
