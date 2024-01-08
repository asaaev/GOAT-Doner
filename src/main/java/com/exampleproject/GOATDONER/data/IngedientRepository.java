package com.exampleproject.GOATDONER.data;

import com.exampleproject.GOATDONER.model.Ingredients;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IngedientRepository extends CrudRepository<Ingredients, String> {

    Iterable<Ingredients> findAll();
    Optional<Ingredients> findById(String id);
    Ingredients save (Ingredients ingredients);

}
