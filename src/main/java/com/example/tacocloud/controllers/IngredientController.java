package com.example.tacocloud.controllers;

import com.example.tacocloud.domain.Ingredient;
import com.example.tacocloud.repostory.IngredientRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@RepositoryRestController
@RequestMapping("ingredients")
public class IngredientController {

  private final IngredientRepository ingredientRepo;

  public IngredientController(IngredientRepository ingredientRepo) {
    this.ingredientRepo = ingredientRepo;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Ingredient> ingredientById(@PathVariable("id") String id) {
    Optional optIngredient = ingredientRepo.findById(id);

    if (optIngredient.isPresent()) {
      return new ResponseEntity<>((Ingredient) optIngredient.get(), HttpStatus.OK);
    }

    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
  }
}
