package com.zosh.recipe_sharing_youtube.repository;

import com.zosh.recipe_sharing_youtube.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);
}
