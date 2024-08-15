package com.example.loginRegister.configuration;


import com.example.loginRegister.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class springConfig {
        @Autowired
    UsersService usersService;

        @Autowired
    passwordEncode passwordEncoder;

        @Bean
    public AuthenticationProvider authenticationProvider(){
            DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
            daoAuthenticationProvider.setUserDetailsService(usersService);
            daoAuthenticationProvider.setPasswordEncoder(passwordEncoder.passwordEncoder());
            return daoAuthenticationProvider;
        }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(authority -> authority

                        .requestMatchers("/register").permitAll()

                      //  .requestMatchers("/login").hasAuthority("ADMIN")

                        .anyRequest().permitAll())

                //.formLogin(Customizer.withDefaults())
                .formLogin(form -> form .loginPage("/login"))
                //.permitAll();
                .httpBasic(Customizer.withDefaults()).csrf(csrf -> csrf.disable());

       return http.build();


    }
}
