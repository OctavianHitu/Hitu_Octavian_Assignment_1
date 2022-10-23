package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.tuc.ds2020.entities.UserOfApp;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserOfApp, UUID> {

    UserOfApp findByEmail(String email);
}
