package edu.pjatk.jcarsapi.service;

import edu.pjatk.jcarsapi.model.User;
import edu.pjatk.jcarsapi.model.UserDetailsImpl;
import edu.pjatk.jcarsapi.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final DrivingLicenseService drivingLicenseService;

    public UserService(UserRepository userRepository, DrivingLicenseService drivingLicenseService) {
        this.userRepository = userRepository;
        this.drivingLicenseService = drivingLicenseService;
    }



    public boolean verifyDrivingLicense(Integer id, String hash) {
        Optional<User> user = userRepository.findById(id);
        if (drivingLicenseService.checkDrivingLicense(hash) && user.isPresent()) {
            user.get().setHasDrivingLicense(true);
            userRepository.save(user.get());
            return true;
        }
        return false;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> getById(Integer id) {
        return userRepository.findById(id);
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }
}