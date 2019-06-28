package com.example.tacocloud.domain.hateoas;

import com.example.tacocloud.domain.Ingredient;
import com.example.tacocloud.domain.Type;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

@Getter
public class IngredientResource extends ResourceSupport {
  private String name;
  private Type type;

  public IngredientResource(Ingredient ingredient) {
    this.name = ingredient.getName();
    this.type = ingredient.getType();
  }
}
