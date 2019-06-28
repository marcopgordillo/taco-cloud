package com.example.tacocloud.domain.hateoas;

import com.example.tacocloud.domain.Ingredient;
import com.example.tacocloud.domain.Taco;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

import java.util.Date;
import java.util.List;

@Getter
public class TacoResource extends ResourceSupport {

  private final String name;
  private final Date createdAt;
  private final List<Ingredient> ingredients;

  public TacoResource(Taco taco) {
    this.name = taco.getName();
    this.createdAt = taco.getCreatedAt();
    this.ingredients = taco.getIngredients();
  }
}
