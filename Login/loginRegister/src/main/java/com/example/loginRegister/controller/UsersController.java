package com.example.loginRegister.controller;

import com.example.loginRegister.model.UsersModel;
import com.example.loginRegister.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsersController {

    private final UsersService usersService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("registerRequest", new UsersModel());
        return "register_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("loginRequest", new UsersModel());
        return "login_page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UsersModel usersModel){
    System.out.println("register request:"+usersModel);
    usersService.registerUser(usersModel);
// UsersModel registeredUser = usersService.registerUser(usersModel.getUser(), usersModel.getPassword(), usersModel.getEmail());
// return registeredUser == null ? "error_page" : "redirect:/login";
        return "hi";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UsersModel usersModel, Model model){
        System.out.println("login request:"+usersModel);
        UsersModel authenticated = usersService.authenticate(usersModel.getUsername(), usersModel.getPassword());
//        if(authenticated!= null){
//            model.addAttribute("userLogin", authenticated.getUser());
//            return "personal_page"; 
//        }else{
//            return "error_page";
//        }
        return "personal_page";
    }
}

