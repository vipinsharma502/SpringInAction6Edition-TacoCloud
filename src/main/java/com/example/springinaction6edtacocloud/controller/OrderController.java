package com.example.springinaction6edtacocloud.controller;

import com.example.springinaction6edtacocloud.model.TacoOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@SessionAttributes("tacoOrder")
@RequestMapping("/orders")
public class OrderController {
    @GetMapping("/current")
    public String orderForm(){
        return "orderForm";
    }

    @PostMapping()
    public String processOrder(TacoOrder tacoOrder, SessionStatus sessionStatus) {
        log.info("order submitted {}", tacoOrder);
        sessionStatus.setComplete();
        return "redirect:/";
    }

}
