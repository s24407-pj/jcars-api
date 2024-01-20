package edu.pjatk.jcarsapi.controller;

import edu.pjatk.jcarsapi.model.User;
import edu.pjatk.jcarsapi.model.UserDetailsImpl;
import edu.pjatk.jcarsapi.model.request.SignupRequest;
import edu.pjatk.jcarsapi.model.response.ApiResponse;
import edu.pjatk.jcarsapi.model.response.JwtResponse;
import edu.pjatk.jcarsapi.repository.RolesRepository;
import edu.pjatk.jcarsapi.repository.UserRepository;
import edu.pjatk.jcarsapi.service.UserService;
import edu.pjatk.jcarsapi.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
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

            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(new ApiResponse(true, "Logowanie przebiegło pomyślnie", new JwtResponse(token, userDetails.getId(), userDetails.getEmail(), roles)));
        }
        catch (BadCredentialsException e)
        {
            return ResponseEntity.ok(new ApiResponse(false, "Nieprawidłowy login lub hasło.", null));
        }
        catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(false, e.getMessage(), null));
        }
    }

    @PostMapping(value = "/signup")
    ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {

        Optional<User> user = userRepository.findByEmail(signupRequest.getEmail());

        if (user.isPresent()) {
            return ResponseEntity.ok(new ApiResponse(false, "Email is already taken", null));
        }

        try {
            User user1 = new User();
            user1.setFirstName(signupRequest.getFirstName());
            user1.setLastName(signupRequest.getLastName());
            user1.setEmail(signupRequest.getEmail());
            user1.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
            user1.setAddress(signupRequest.getAddress());
            user1.setPhoneNumber(signupRequest.getPhoneNumber());
            user1.setHasDrivingLicense(signupRequest.getHasDrivingLicense());
            userRepository.save(user1);
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(false, e.getMessage(), null));
        }

        return ResponseEntity.ok(new ApiResponse(true, "Successfully registered!", null));
    }
}