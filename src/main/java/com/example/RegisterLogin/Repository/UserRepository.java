package com.example.RegisterLogin.Repository;

import com.example.RegisterLogin.Entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface UserRepository extends JpaRepository<AppUser,Integer> {
    Optional<AppUser> findOneByEmailAndPassword(String email, String password);
    AppUser findByEmail(String email);

}