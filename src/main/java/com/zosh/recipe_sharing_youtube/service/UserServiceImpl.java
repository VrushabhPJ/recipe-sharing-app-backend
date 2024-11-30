package com.zosh.recipe_sharing_youtube.service;

import com.zosh.recipe_sharing_youtube.config.JwtProvider;
import com.zosh.recipe_sharing_youtube.model.User;
import com.zosh.recipe_sharing_youtube.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private JwtProvider jwtProvider;

    public UserServiceImpl(UserRepository userRepository1, JwtProvider jwtProvider) {
        this.userRepository = userRepository1;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public User findUserById(Long userId) throws Exception {
        Optional<User> user= userRepository.findById(userId);
        if(user.isPresent()) {
            return user.get();
        }
        throw new Exception("User not found with id "+ userId);
    }

    @Override
    public User findUserByJwt(String jwt) throws Exception {
        String email= jwtProvider.getEmailFromJwtToken(jwt);

        if(email == null) {
            throw new Exception("provide valid jwt token...");
        }

        User user= userRepository.findByEmail(email);
        if(user == null) {
            throw new Exception("user not found with email "+email);
        }

        return user;
    }
}
