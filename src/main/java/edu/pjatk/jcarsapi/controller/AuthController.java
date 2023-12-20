package edu.pjatk.jcarsapi.controller;

import edu.pjatk.jcarsapi.model.Enums.ERoles;
import edu.pjatk.jcarsapi.model.Role;
import edu.pjatk.jcarsapi.model.User;
import edu.pjatk.jcarsapi.model.UserDetailsImpl;
import edu.pjatk.jcarsapi.model.request.SignupRequest;
import edu.pjatk.jcarsapi.model.response.JwtResponse;
import edu.pjatk.jcarsapi.model.response.Login;
import edu.pjatk.jcarsapi.repository.RolesRepository;
import edu.pjatk.jcarsapi.repository.UserRepository;
import edu.pjatk.jcarsapi.service.UserService;
import edu.pjatk.jcarsapi.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final RolesRepository rolesRepository;

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, AuthenticationManager authenticationManager, RolesRepository rolesRepository, PasswordEncoder passwordEncoder, UserService userService, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody edu.pjatk.jcarsapi.model.request.Login loginReq) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginReq.getEmail(), loginReq.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtUtil.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            Login loginRes = new Login(userDetails.getEmail(), token);

            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            return ResponseEntity.ok(new JwtResponse(token, userDetails.getId(), userDetails.getEmail(), roles));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping(value = "/signup")
    ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {

        Optional<User> user = userRepository.findByEmail(signupRequest.getEmail());

        if (user.isPresent()) {
            return ResponseEntity.badRequest().body("Email is already taken");
        }


        try {
            Set<String> strRole = signupRequest.getRole();
            Set<Role> roles = new HashSet<>();

            if (strRole == null) {
                Role userRole = rolesRepository.findByName(ERoles.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                roles.add(userRole);
            } else {
                strRole.forEach(role -> {
                    switch (role) {
                        case "admin":
                            Role adminRole = rolesRepository.findByName(ERoles.ROLE_ADMIN)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                            roles.add(adminRole);
                            break;
                        default:
                            Role userRole = rolesRepository.findByName(ERoles.ROLE_USER)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                            roles.add(userRole);
                    }
                });
            }
            User user1 = new User();
            user1.setFirstName(signupRequest.getFirstName());
            user1.setLastName(signupRequest.getLastName());
            user1.setEmail(signupRequest.getEmail());
            user1.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
            user1.setAddress(signupRequest.getAddress());
            user1.setPhoneNumber(signupRequest.getPhoneNumber());
            user1.setHasDrivingLicense(signupRequest.getHasDrivingLicense());
            user1.setRoles(roles);
            userRepository.save(user1);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok("Successfully registered!");
    }
}