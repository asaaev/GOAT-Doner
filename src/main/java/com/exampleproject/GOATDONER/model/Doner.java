package com.exampleproject.GOATDONER.model;

import lombok.Data;

import java.util.List;

@Data
public class Doner {

    private String name;
    private List<Ingredients> ingredient;

}
