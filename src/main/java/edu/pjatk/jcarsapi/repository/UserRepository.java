package edu.pjatk.jcarsapi.repository;

import edu.pjatk.jcarsapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    @Transactional
    @Modifying
    @Query("update User u set u.hasDrivingLicense = ?1 where u.id = ?2")
    void updateHasDrivingLicenseById(@NonNull Boolean hasDrivingLicense, @NonNull Integer id);

}
