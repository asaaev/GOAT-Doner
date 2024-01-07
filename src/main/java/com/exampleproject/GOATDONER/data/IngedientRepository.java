package com.exampleproject.GOATDONER.data;

import com.exampleproject.GOATDONER.model.Ingredients;

import java.util.Optional;

public interface IngedientRepository {

    Iterable<Ingredients> findAll();
    Optional<Ingredients> findById(String id);
    Ingredients save (Ingredients ingredients);

}
