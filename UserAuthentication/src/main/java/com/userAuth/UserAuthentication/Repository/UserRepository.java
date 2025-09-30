package com.userAuth.UserAuthentication.Repository;

import com.userAuth.UserAuthentication.Domain.Users.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository <Users, String>{
    Users findByLogin(String login);
}
