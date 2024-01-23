package edu.pjatk.jcarsapi.service;

import edu.pjatk.jcarsapi.model.Enums.ERoles;
import edu.pjatk.jcarsapi.model.User;
import edu.pjatk.jcarsapi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    private User dummyUser;
    private User dummyUser2;

    @Mock
    private UserRepository userRepository;
    @Mock
    private DrivingLicenseService drivingLicenseService;

    @InjectMocks
    private UserService underTest;

    @BeforeEach
    void setUp() {
        dummyUser = new User(1, "John", "Doe", "john.doe@example.com", "password123", "1234567890", "123 Sample Street", true, ERoles.ROLE_USER);
        dummyUser2 = new User(2, "John", "Doe", "2john.doe@example.com", "password123", "1234567890", "123 Sample Street", false, ERoles.ROLE_USER);
    }

    @Test
    void getAll() {
        List<User> expected = List.of(dummyUser, dummyUser2);
        when(userRepository.findAll()).thenReturn(expected);

        var actual = underTest.getAll();

        assertEquals(expected, actual);
    }

    @Test
    void getById() {
        int id = dummyUser.getId();
        var expected = Optional.of(dummyUser);

        when(userRepository.findById(id)).thenReturn(expected);

        var actual = underTest.getById(id);

        assertEquals(expected, actual);
    }

    @Test
    void deleteById() {
        int id = dummyUser.getId();

        underTest.deleteById(id);

        verify(userRepository, times(1)).deleteById(id);
    }

    @Test
    void shouldVerifyDrivingLicense() {
        int id = 1;
        String hash = "someHash";

        when(userRepository.existsById(id)).thenReturn(true);
        when(drivingLicenseService.checkDrivingLicense(hash)).thenReturn(true);

        boolean result = underTest.verifyDrivingLicense(id, hash);

        verify(userRepository, times(1)).updateHasDrivingLicenseById(true, id);
        assertTrue(result);

    }

    @Test
    void shouldNotVerifyDrivingLicense() {
        int id = 1;
        String hash = "someHash";

        when(userRepository.existsById(id)).thenReturn(true);
        when(drivingLicenseService.checkDrivingLicense(hash)).thenReturn(false);

        boolean result = underTest.verifyDrivingLicense(id, hash);

        verify(userRepository, times(0)).updateHasDrivingLicenseById(anyBoolean(), anyInt());
        assertFalse(result);

    }
}