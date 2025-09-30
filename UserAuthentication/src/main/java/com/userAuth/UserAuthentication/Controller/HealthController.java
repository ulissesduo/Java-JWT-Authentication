package com.userAuth.UserAuthentication.Controller;

import com.userAuth.UserAuthentication.Domain.Users.AuthenticationDTO;
import com.userAuth.UserAuthentication.Repository.UserRepository;
import jakarta.validation.Valid;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    private final MongoTemplate mongoTemplate;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    public HealthController(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @GetMapping("/health")
    public String checkConnection() {
        try {
            // This is a basic MongoDB ping command
            Document result = mongoTemplate.executeCommand("{ ping: 1 }");
            return "MongoDB connection OK: " + result.toJson();
        } catch (Exception e) {
            return "MongoDB connection FAILED: " + e.getMessage();
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        try {
            // This is a basic MongoDB ping command
            Document result = mongoTemplate.executeCommand("{ ping: 1 }");
            return ResponseEntity.ok(result.toJson());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
//        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
//        var auth = this.authenticationManager.authenticate(usernamePassword);
//        return ResponseEntity.ok(auth);

    }
}
