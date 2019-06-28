package com.example.tacocloud.domain.hateoas;

import com.example.tacocloud.controllers.assembler.IngredientResourceAssembler;
import com.example.tacocloud.domain.Taco;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import java.util.Date;
import java.util.List;

@Getter
@Relation(value = "taco", collectionRelation = "tacos")
public class TacoResource extends ResourceSupport {

  private static final IngredientResourceAssembler ingredientAssembler = new IngredientResourceAssembler();

  private final String name;
  private final Date createdAt;
  private final List<IngredientResource> ingredients;

  public TacoResource(Taco taco) {
    this.name = taco.getName();
    this.createdAt = taco.getCreatedAt();
    this.ingredients = ingredientAssembler.toResources(taco.getIngredients());
  }
}
