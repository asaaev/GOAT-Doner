package com.exampleproject.GOATDONER.controllers;

import com.exampleproject.GOATDONER.model.DonerOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
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
    public String processOrder(DonerOrder order, SessionStatus status){
        log.info("Order Submitted: {}", order);
        status.setComplete();
        return "redirect:/homepage";
    }
}
