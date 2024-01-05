package com.exampleproject.GOATDONER.utility;

import com.exampleproject.GOATDONER.model.Ingredients;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredients> {

    private Map<String, Ingredients> ingredientsMap = new HashMap<>();

    public IngredientByIdConverter(){
        ingredientsMap.put("FLTO",
                new Ingredients("FLTO", "Flour Tortilla", Ingredients.Type.WRAP));
        ingredientsMap.put("COTO",
                new Ingredients("COTO", "Corn Tortilla", Ingredients.Type.WRAP));
        ingredientsMap.put("GRBF",
                new Ingredients("GRBF", "Ground Beef", Ingredients.Type.PROTEIN));
        ingredientsMap.put("CARN",
                new Ingredients("CARN", "Carnitas", Ingredients.Type.PROTEIN));
        ingredientsMap.put("TMTO",
                new Ingredients("TMTO", "Diced Tomatoes", Ingredients.Type.VEGGIES));
        ingredientsMap.put("LETC",
                new Ingredients("LETC", "Lettuce", Ingredients.Type.VEGGIES));
        ingredientsMap.put("CHED",
                new Ingredients("CHED", "Cheddar", Ingredients.Type.CHEESE));
        ingredientsMap.put("JACK",
                new Ingredients("JACK", "Monterrey Jack", Ingredients.Type.CHEESE));
        ingredientsMap.put("SLSA",
                new Ingredients("SLSA", "Salsa", Ingredients.Type.SAUCE));
        ingredientsMap.put("SRCR",
                new Ingredients("SRCR", "Sour Cream", Ingredients.Type.SAUCE));
    }

    @Override
    public Ingredients convert(String id) {
        return ingredientsMap.get(id);
    }

}
