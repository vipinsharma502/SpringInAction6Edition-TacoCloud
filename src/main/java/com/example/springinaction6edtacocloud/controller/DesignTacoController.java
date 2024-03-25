package com.example.springinaction6edtacocloud.controller;

import com.example.springinaction6edtacocloud.model.Ingredient;
import com.example.springinaction6edtacocloud.model.Taco;
import com.example.springinaction6edtacocloud.model.TacoOrder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.springinaction6edtacocloud.model.Ingredient.Type.*;

@Slf4j
@Controller
@SessionAttributes("tacoOrder")
@RequestMapping("/design")
public class DesignTacoController {
    @ModelAttribute
    public void addIngredientToModel(Model model) {
        log.info("inside method addIngredientToModel");
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", WRAP),
                new Ingredient("COTO", "Corn Tortilla", WRAP),
                new Ingredient("GRBF", "Ground Beef", PROTEIN),
                new Ingredient("CARN", "Carnitas", PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", VEGGIES),
                new Ingredient("LETC", "Lettuce", VEGGIES),
                new Ingredient("CHED", "Cheddar", CHEESE),
                new Ingredient("JACK", "Monterrey Jack", CHEESE),
                new Ingredient("SLSA", "Salsa", SAUCE),
                new Ingredient("SRCR", "Sour Cream", SAUCE)
        );
        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type: types ) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        log.info("inside order method");
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco(Model model){
        log.info("inside taco method");
        return new Taco();
    }

    @GetMapping()
    public String showDesignForm(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        log.info("session is {}", session.getAttribute("tacoOrder"));
        log.info("session id {}", session.getId());
        log.info("Model is {}", model);
        log.info("inside showDesignForm controller");
        log.info("request.getAttribute: {}", request.getAttribute("tacoOrder"));
        return "design";
    }

    @PostMapping
    public String processTaco(HttpServletRequest request, @Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {
        log.info("taco is {}", taco);
        log.info("taco order is {}", tacoOrder);
        if(errors.hasErrors()) {
            return "design";
        }
        tacoOrder.addTaco(taco);
        return "redirect:/orders/current";
    }


    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients.stream().filter( ingredient -> ingredient.getType().equals(type)).collect(Collectors.toSet());
    }
}
