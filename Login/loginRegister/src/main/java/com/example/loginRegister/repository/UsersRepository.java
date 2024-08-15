package com.example.loginRegister.repository;

import com.example.loginRegister.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UsersRepository extends JpaRepository<UsersModel, Integer> {
    public Optional<UsersModel>findByUsernameAndPassword(String user, String password);

    UserDetails findByUsername(String username);
    //Optional<UsersModel> findFirstByLogin(String login);
}
