package com.zosh.recipe_sharing_youtube.controller;

import com.zosh.recipe_sharing_youtube.config.JwtProvider;
import com.zosh.recipe_sharing_youtube.model.User;
import com.zosh.recipe_sharing_youtube.repository.UserRepository;
import com.zosh.recipe_sharing_youtube.request.LoginRequest;
import com.zosh.recipe_sharing_youtube.response.AuthResponse;
import com.zosh.recipe_sharing_youtube.service.CustomeUserDetailsService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private UserRepository userRepository;
    private CustomeUserDetailsService customeUserDetailsService;
    private JwtProvider jwtProvider;
    private PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, CustomeUserDetailsService customeUserDetailsService,
                          JwtProvider jwtProvider, PasswordEncoder passwordEncoder) {
        this.userRepository=userRepository;
        this.customeUserDetailsService=customeUserDetailsService;
        this.jwtProvider=jwtProvider;
        this.passwordEncoder=passwordEncoder;
    }

    @PostMapping("/signup") // used for create new profile
    public AuthResponse createUser(@RequestBody User user) throws Exception {
        String email= user.getEmail();
        String password= user.getPassword();
        String fullName= user.getFullName();

        User isExitEmail= userRepository.findByEmail(email);
        if(isExitEmail != null) {
            throw new Exception("email is already used with another account");
        }

        User createdUser= new User();
        createdUser.setEmail(email);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setFullName(fullName);

        User savedUser= userRepository.save(createdUser);

        Authentication authentication= new UsernamePasswordAuthenticationToken(email, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token=jwtProvider.generateToken(authentication);

        AuthResponse response= new AuthResponse();

        response.setJwt(token);
        response.setMessage("signup success");

        return response;
    }

    @PostMapping("/signin")     //used for login purpose
    public AuthResponse signinHandler(@RequestBody LoginRequest loginRequest) {
        String username= loginRequest.getEmail();
        String password= loginRequest.getPassword();

        Authentication authentication= authenticate(username,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token=jwtProvider.generateToken(authentication);

        AuthResponse response= new AuthResponse();

        response.setJwt(token);
        response.setMessage("sign-in success");

        return response;
    }

    private Authentication authenticate(String username, String password) {

        UserDetails userDetails= customeUserDetailsService.loadUserByUsername(username);

        if(userDetails == null) {
            throw new BadCredentialsException("User not found");
        }
        if(!passwordEncoder.matches(password , userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,
                null, userDetails.getAuthorities());
    }
}
