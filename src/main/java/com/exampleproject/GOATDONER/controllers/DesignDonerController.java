package com.exampleproject.GOATDONER.controllers;

import com.exampleproject.GOATDONER.data.IngedientRepository;
import com.exampleproject.GOATDONER.model.Doner;
import com.exampleproject.GOATDONER.model.DonerOrder;
import com.exampleproject.GOATDONER.model.Ingredients;
import com.exampleproject.GOATDONER.model.Ingredients.Type;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("donerOrder")
public class DesignDonerController {

    private final IngedientRepository ingedientRepository;

    @Autowired
    public DesignDonerController(IngedientRepository ingedientRepository) {
        this.ingedientRepository = ingedientRepository;
    }


    @ModelAttribute
    public void addIngredientsToDoner(Model model) {
        Iterable<Ingredients> ingredientsIterable = ingedientRepository.findAll();
        List<Ingredients> ingredientsList = StreamSupport.stream(ingredientsIterable.spliterator(), false)
                .collect(Collectors.toList());
        Type[] types = Ingredients.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredientsList, type));
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
    public String processDoner (@Valid Doner doner, Errors errors, @ModelAttribute DonerOrder donerOrder){
        if (errors.hasErrors()) {
            return "design";
        }
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
