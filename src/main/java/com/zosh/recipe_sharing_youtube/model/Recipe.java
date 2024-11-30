package com.zosh.recipe_sharing_youtube.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String image;

    private String description;

    private boolean vegetarian;

    private LocalDateTime createdAt;

    private List<Long> likes= new ArrayList<>();

}