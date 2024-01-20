package edu.pjatk.jcarsapi.controller;

import edu.pjatk.jcarsapi.exception.ResourceNotFoundException;
import edu.pjatk.jcarsapi.model.User;
import edu.pjatk.jcarsapi.model.response.ApiResponse;
import edu.pjatk.jcarsapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users/{id}/license/{hash}")
    ResponseEntity<Boolean> confirmLicense(
            @PathVariable Integer id,
            @PathVariable String hash) {
       return new ResponseEntity<> (userService.verifyDrivingLicense(id,hash),HttpStatus.OK);

    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        Optional<User> user = userService.getById(id);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }

    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {

        if (userService.getById(id).isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }

        userService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
