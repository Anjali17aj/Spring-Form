package com.example.loginRegister.service;

import com.example.loginRegister.model.UsersModel;
import com.example.loginRegister.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UsersService(UsersRepository userRepository) {
        this.usersRepository = userRepository;
    }
    public UsersModel registerUser(UsersModel usersModel){
        
        usersModel.setPassword(passwordEncoder.encode(usersModel.getPassword()));;
        return usersRepository.save(usersModel);
    }
//    public UsersModel registerUser(String login, String password, String email){
//        if(login==null && password==null) {
//            return null;
//        }else{
//            if (usersRepository.findFirstByLogin(login).isPresent()) {
//                System.out.println("Duplicate login");
//                return null;
//            }
//            UsersModel usersModel = new UsersModel();
//            usersModel.setUser(login);
//            usersModel.setPassword(password);
//            usersModel.setEmail(email);
//            return usersRepository.save(usersModel);
//        }
//
//        }
        public UsersModel authenticate(String user, String password){
            System.out.println("Reached upto service class");
        return usersRepository.findByUsernameAndPassword(user, password).orElse(null);
        }
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return usersRepository.findByUser(username);
//    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersModel user = (UsersModel) usersRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return user;
    }
}