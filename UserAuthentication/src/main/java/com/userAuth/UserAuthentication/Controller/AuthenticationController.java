package com.userAuth.UserAuthentication.Controller;

import com.userAuth.UserAuthentication.Domain.Users.AuthenticationDTO;
import com.userAuth.UserAuthentication.Domain.Users.LoginResponseDTO;
import com.userAuth.UserAuthentication.Domain.Users.RegisterDTO;
import com.userAuth.UserAuthentication.Domain.Users.Users;
import com.userAuth.UserAuthentication.Infra.Security.TokenService;
import com.userAuth.UserAuthentication.Repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Users) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));

    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password()); //get password hashed
        Users newUser = new Users(data.login(), encryptedPassword, data.role());

        this.userRepository.save(newUser);
        return ResponseEntity.ok(newUser);
    }



}
