package edu.pjatk.jcarsapi.controller;

import edu.pjatk.jcarsapi.model.request.LoginRequest;
import edu.pjatk.jcarsapi.model.response.LoginResponse;
import edu.pjatk.jcarsapi.service.UserService;
import edu.pjatk.jcarsapi.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/auth")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequestReq) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestReq.email(), loginRequestReq.password())
            );

            UserDetails user = userService.loadUserByUsername(authentication.getName());
            String token = jwtUtil.createToken(user);
            LoginResponse loginRes = new LoginResponse(user.getUsername(), token);

            return ResponseEntity.ok(loginRes);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}