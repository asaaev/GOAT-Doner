package com.exampleproject.GOATDONER.controllers;

import com.exampleproject.GOATDONER.model.Doner;
import com.exampleproject.GOATDONER.model.DonerOrder;
import com.exampleproject.GOATDONER.model.Ingredients;
import com.exampleproject.GOATDONER.model.Ingredients.Type;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("donerOrder")
public class DesignDonerController {

    @ModelAttribute
    public void addIngredientsToDoner(Model model){
        List<Ingredients> ingredientsList = Arrays.asList(
                new Ingredients("PIBD", "Pita Bread", Type.WRAP),
                new Ingredients("LVSH", "Lavash", Type.WRAP),
                new Ingredients("GRBF", "Ground Beef", Type.PROTEIN),
                new Ingredients("CARN", "Carnitas", Type.PROTEIN),
                new Ingredients("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new Ingredients("LETC", "Lettuce", Type.VEGGIES),
                new Ingredients("CHED", "Cheddar", Type.CHEESE),
                new Ingredients("JACK", "Monterrey Jack", Type.CHEESE),
                new Ingredients("SLSA", "Salsa", Type.SAUCE),
                new Ingredients("SRCR", "Sour Cream", Type.SAUCE)
        );

        Type[] types = Ingredients.Type.values();
        for (Type type : types){
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredientsList, type));
        }
    }
    @ModelAttribute(name = "donerOrder")
    public DonerOrder donerOrder(){
        return new DonerOrder();
    }
    @ModelAttribute(name = "doner")
    public Doner doner(){
        return new Doner();
    }
    @GetMapping
    public String showDesignForm(){
        return "design";
    }
    @PostMapping
    public String processDoner (Doner doner, @ModelAttribute DonerOrder donerOrder){
        donerOrder.addDoner(doner);
        log.info("Processing doner: {}", doner);
        return "redirect:/orders/current";
    }
    private Iterable<Ingredients> filterByType(List<Ingredients> ingredientsList, Type type){
        return ingredientsList
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }


}
