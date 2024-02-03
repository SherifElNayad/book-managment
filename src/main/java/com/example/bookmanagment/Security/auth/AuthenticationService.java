package com.example.bookmanagment.Security.auth;

import com.example.bookmanagment.Entities.user.Role;
import com.example.bookmanagment.Security.Config.JwtService;
import com.example.bookmanagment.Entities.user.User;
import com.example.bookmanagment.Repos.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .username(request.getEmail())
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        Optional<User> user = userRepository.findByEmail(request.getEmail());

        if(user.isEmpty()){
            throw new BadCredentialsException("login.credentials.not.match");
        }
//        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword()
                    )
            );
//        }
//        catch (BadCredentialsException e){
//            throw new BadCredentialsException("Bad Credentials");
//        }
        var jwtToken = jwtService.generateToken(user.get());
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
