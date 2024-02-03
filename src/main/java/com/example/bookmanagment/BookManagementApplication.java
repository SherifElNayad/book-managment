package com.example.bookmanagment;

import com.example.bookmanagment.Entities.user.Role;
import com.example.bookmanagment.Security.auth.AuthenticationRequest;
import com.example.bookmanagment.Security.auth.AuthenticationService;
import com.example.bookmanagment.Security.auth.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookManagementApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AuthenticationService authenticationService) {
        return args -> {
            var adminAuth = AuthenticationRequest.builder()
                    .email("Admin@Admin.com")
                    .password("1234")
                    .build();
            var userAuth = AuthenticationRequest.builder()
                    .email("User@User.com")
                    .password("1234")
                    .build();
            if(authenticationService.authenticate(adminAuth) != null){
                System.out.println("Admin token :" + authenticationService.authenticate(adminAuth).getToken() );
                System.out.println("User token :" + authenticationService.authenticate(userAuth).getToken() );
                return;
            }
            var admin = RegisterRequest.builder()
                    .firstName("Admin")
                    .email("Admin@Admin.com")
                    .password("1234")
                    .role(Role.ADMIN)
                    .build();
            System.out.println("Admin token :" + authenticationService.register(admin).getToken() );

            var user = RegisterRequest.builder()
                    .firstName("user")
                    .email("user@user.com")
                    .password("1234")
                    .role(Role.USER)
                    .build();
            System.out.println("User token :" + authenticationService.register(user).getToken() );

        };
    }
}
