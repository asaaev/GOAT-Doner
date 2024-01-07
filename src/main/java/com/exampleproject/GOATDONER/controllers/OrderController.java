package com.exampleproject.GOATDONER.controllers;

import com.exampleproject.GOATDONER.model.DonerOrder;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("donerOrder")
public class OrderController {

    @GetMapping("/current")
    public String orderForm(){
        return "orderForm";
    }
    @PostMapping()
    public String processOrder(@Valid DonerOrder order, Errors errors, SessionStatus status){
        if (errors.hasErrors()){
            return "orderForm";
        }
        log.info("Order Submitted: {}", order);
        status.setComplete();
        return "redirect:/home";
    }
}
