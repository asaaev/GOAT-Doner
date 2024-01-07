package com.exampleproject.GOATDONER.utility;

import com.exampleproject.GOATDONER.data.IngedientRepository;
import com.exampleproject.GOATDONER.model.Ingredients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredients> {

    private IngedientRepository ingedientRepository;

    @Autowired
    public IngredientByIdConverter(IngedientRepository ingedientRepository){
        this.ingedientRepository = ingedientRepository;
    }

    @Override
    public Ingredients convert(String id) {
        return ingedientRepository.findById(id).orElse(null);
    }

}
