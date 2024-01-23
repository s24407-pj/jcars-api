package edu.pjatk.jcarsapi.controller;

import edu.pjatk.jcarsapi.exception.ResourceNotFoundException;
import edu.pjatk.jcarsapi.model.EditPassword;
import edu.pjatk.jcarsapi.model.User;
import edu.pjatk.jcarsapi.model.request.EditProfile;
import edu.pjatk.jcarsapi.repository.UserRepository;
import edu.pjatk.jcarsapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/jcars")
public class UserController {
    private final UserService userService;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public UserController(UserService userService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/users/{id}/license/{hash}")
    ResponseEntity<Boolean> confirmLicense(@PathVariable Integer id, @PathVariable String hash) {
        return new ResponseEntity<>(userService.verifyDrivingLicense(id, hash), HttpStatus.OK);

    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        Optional<User> user = userService.getById(id);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {

        if (userService.getById(id).isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }

        userService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/users/pass/{id}")
    public ResponseEntity<String> updatePass(@PathVariable Integer id, @RequestBody EditPassword editPassword) {
        Optional<User> user = userService.getById(id);
        if (user.isPresent()) {
            if (passwordEncoder.matches(editPassword.getOldPassword(), user.get().getPassword())) {
                try {
                    user.get().setPassword(passwordEncoder.encode(editPassword.getNewPassword()));
                    user.get().getVerified().setPasswordIsChanged(true);
                    userRepository.save(user.get());
                    return ResponseEntity.ok().body("Successfully password has been changed");
                } catch (Exception e) {
                    return ResponseEntity.badRequest().body(e.getMessage());
                }
            } else {
                throw new ResourceAccessException("Old password is not correct");
            }
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<String> updateVerified(@PathVariable Integer id, @RequestBody EditProfile editProfile) {
        Optional<User> user = userService.getById(id);
        if (user.isPresent()) {
            try {
                if (!editProfile.getAddress().isEmpty()) {
                    user.get().setAddress(editProfile.getAddress());
                    user.get().getVerified().setAddressIsChanged(true);
                }
                if (!editProfile.getPhone().isEmpty()) {
                    user.get().setPhoneNumber(editProfile.getPhone());
                    user.get().getVerified().setPhoneIsChanged(true);
                }
                userRepository.save(user.get());
                return ResponseEntity.ok().body("Successfully updated");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }
}
