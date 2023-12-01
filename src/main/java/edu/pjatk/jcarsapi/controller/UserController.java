package edu.pjatk.jcarsapi.controller;

import edu.pjatk.jcarsapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/{id}/license/{hash}")
    ResponseEntity<Boolean> verifyDrivingLicense(
            @PathVariable Integer id,
            @PathVariable String hash) {
        Boolean isVerified =  userService.verifyDrivingLicense(id,hash);
        return ResponseEntity.ok().body(isVerified);
    }
}
