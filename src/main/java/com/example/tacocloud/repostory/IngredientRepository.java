package com.example.tacocloud.repostory;

import com.example.tacocloud.domain.Ingredient;

public interface IngredientRepository {

  Iterable<Ingredient> findAll();

  Ingredient findById(String id);

  Ingredient save(Ingredient ingredient);
}
