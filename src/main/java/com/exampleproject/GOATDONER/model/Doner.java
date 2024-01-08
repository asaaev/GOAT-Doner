package com.exampleproject.GOATDONER.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Doner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date createdAt = new Date(System.currentTimeMillis());
    @NotNull
    @Size(min = 4, message = "Name must be at least 4 characters long")
    @Size(max = 26, message = "Name cannot be grater then 26 characters")
    private String name;
    @NotNull
    @Size(min = 1, message = "You must choose at least one ingredient")
    @ManyToMany
    private List<Ingredients> ingredients = new ArrayList<>();
    public void addIngredient(Ingredients ingredient) {
        this.ingredients.add(ingredient);
    }

}
