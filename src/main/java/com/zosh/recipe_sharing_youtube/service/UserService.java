package com.zosh.recipe_sharing_youtube.service;
import com.zosh.recipe_sharing_youtube.model.User;


public interface UserService {
    public User findUserById(Long userId) throws Exception;

    public User findUserByJwt(String jwt) throws Exception;

}
