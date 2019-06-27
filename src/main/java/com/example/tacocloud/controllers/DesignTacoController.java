package com.example.tacocloud.controllers;

import com.example.tacocloud.domain.Ingredient;
import com.example.tacocloud.domain.Order;
import com.example.tacocloud.domain.Taco;
import com.example.tacocloud.domain.Type;
import com.example.tacocloud.repostory.IngredientRepository;
import com.example.tacocloud.repostory.TacoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

  private final IngredientRepository ingredientRepo;
  private final TacoRepository designRepo;

  public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository designRepo) {
    this.ingredientRepo = ingredientRepo;
    this.designRepo = designRepo;
  }

  @ModelAttribute
  public void addIngredientsToModel(Model model) {
    List<Ingredient> ingredients = new ArrayList<>();
    ingredientRepo.findAll().forEach(ingredients::add);

    Type[] types = Type.values();

    for (Type type : types) {
      model.addAttribute(type.toString().toLowerCase(),
              filterByType(ingredients, type));
    }
  }

  @ModelAttribute(name = "order")
  public Order order() {
    return new Order();
  }

  @ModelAttribute(name = "taco")
  public Taco taco() {
    return new Taco();
  }

  @GetMapping
  public String showDesignForm() {
    return "design";
  }

  @PostMapping
  public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute("order") Order order) {
    if (errors.hasErrors()) {
      return "design";
    }

    Taco saved = designRepo.save(design);
    order.addDesign(saved);

    log.info("Processing design: " + design);
    return "redirect:/orders/current";
  }

  private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
    return ingredients
            .stream()
            .filter(x -> x.getType().equals(type))
            .collect(Collectors.toList());
  }
}
