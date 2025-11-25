package gr.pwc.assignment.authorizedpersonnel.controller;

import gr.pwc.assignment.authorizedpersonnel.model.dto.JwtResponse;
import gr.pwc.assignment.authorizedpersonnel.model.dto.LoginRequest;
import gr.pwc.assignment.authorizedpersonnel.service.CustomUserDetailsService;
import gr.pwc.assignment.common.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authManager,
                          CustomUserDetailsService customUserDetailsService,
                          JwtUtil jwtUtil) {
        this.authManager = authManager;
        this.customUserDetailsService = customUserDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public JwtResponse login(@RequestBody @Valid LoginRequest req) {

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.username(), req.password()));

        var user = this.customUserDetailsService.loadUserByUsername(req.username());

        String token = jwtUtil.generateToken(user);

        return new JwtResponse(token);
    }
}
