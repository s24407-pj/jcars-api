package edu.pjatk.jcarsapi.service;

import edu.pjatk.jcarsapi.model.User;
import edu.pjatk.jcarsapi.repository.UserRepository;
import org.springframework.stereotype.Service;

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


    public Boolean verifyDrivingLicense(Integer id, String hash) {
        if (userRepository.existsById(id) && drivingLicenseService.checkDrivingLicense(hash)) {
            userRepository.updateHasDrivingLicenseById(true, id);
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