package com.zosh.recipe_sharing_youtube.service;

import com.zosh.recipe_sharing_youtube.model.User;
import com.zosh.recipe_sharing_youtube.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//this class for "don't generate default password" we use our own jwt token for authenticate.
@Service
public class CustomeUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public CustomeUserDetailsService(UserRepository userRepository) {
        this.userRepository= userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= userRepository.findByEmail(username);

        if(user == null) {
            throw new UsernameNotFoundException("user not found with email "+ username);
        }

        List<GrantedAuthority> authorities= new ArrayList<>();

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);

    }
}
