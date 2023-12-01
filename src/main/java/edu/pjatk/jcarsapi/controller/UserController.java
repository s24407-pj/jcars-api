package edu.pjatk.jcarsapi.controller;

import edu.pjatk.jcarsapi.exception.ResourceNotFoundException;
import edu.pjatk.jcarsapi.model.User;
import edu.pjatk.jcarsapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users/{id}/license/{hash}")
    ResponseEntity<Boolean> verifyDrivingLicense(
            @PathVariable Integer id,
            @PathVariable String hash) {
        Boolean isVerified = userService.verifyDrivingLicense(id, hash);
        return ResponseEntity.ok().body(isVerified);
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
}
