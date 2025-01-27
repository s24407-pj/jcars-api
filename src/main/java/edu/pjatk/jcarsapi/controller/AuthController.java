package edu.pjatk.jcarsapi.controller;

import edu.pjatk.jcarsapi.model.Enums.ERoles;
import edu.pjatk.jcarsapi.model.User;
import edu.pjatk.jcarsapi.model.UserDetailsImpl;
import edu.pjatk.jcarsapi.model.Verified;
import edu.pjatk.jcarsapi.model.request.SignupRequest;
import edu.pjatk.jcarsapi.model.response.JwtResponse;
import edu.pjatk.jcarsapi.repository.UserRepository;
import edu.pjatk.jcarsapi.service.UserService;
import edu.pjatk.jcarsapi.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, UserService userService, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody edu.pjatk.jcarsapi.model.request.LoginRequest loginReq) {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginReq.email(), loginReq.password())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtUtil.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            /*Login loginRes = new Login(userDetails.getEmail(), token);*/

            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(new JwtResponse(userDetails.getFirstname(), userDetails.getLastname(), userDetails.getAddress(), userDetails.getPhone(), token, userDetails.getId(), userDetails.getEmail(), roles, userDetails.getVerified()));

    }

    @PostMapping(value = "/signup")
    ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {

        Optional<User> user = userRepository.findByEmail(signupRequest.getEmail());

        if (user.isPresent()) {
            return ResponseEntity.badRequest().body("Email is already taken");
        }

            Verified verified = new Verified();
            User user1 = new User();
            user1.setFirstName(signupRequest.getFirstName());
            user1.setLastName(signupRequest.getLastName());
            user1.setEmail(signupRequest.getEmail());
            user1.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
            user1.setAddress(signupRequest.getAddress());
            user1.setPhoneNumber(signupRequest.getPhoneNumber());
            user1.setHasDrivingLicense(signupRequest.getHasDrivingLicense());
            user1.setRole(ERoles.ROLE_USER);
            user1.setVerified(verified);
            userRepository.save(user1);



        return ResponseEntity.ok("Successfully registered!");
    }
}