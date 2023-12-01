package edu.pjatk.jcarsapi.service;

import edu.pjatk.jcarsapi.model.User;
import edu.pjatk.jcarsapi.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
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

    // Other service methods ...

    // This method is extended to comply with Spring Security UserDetailsService interface
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

        // Leveraging the built-in org.springframework.security.core.userdetails.User for UserDetails implementation
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                new ArrayList<>() // List of granted authorities, can be configured based on roles
        );
    }

    public boolean verifyDrivingLicense(Integer id, String hash) {
        Optional<User> user = userRepository.findById(id);
       if (drivingLicenseService.checkDrivingLicense(hash) && user.isPresent()){
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