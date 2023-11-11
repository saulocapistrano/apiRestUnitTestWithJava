package com.ifinit.apiresttestes.repositories;

import com.ifinit.apiresttestes.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByMail(String mail);

    class UserResource {
    }
}

