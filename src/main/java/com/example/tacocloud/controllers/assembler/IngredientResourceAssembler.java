package com.example.tacocloud.controllers.assembler;

import com.example.tacocloud.controllers.IngredientController;
import com.example.tacocloud.domain.Ingredient;
import com.example.tacocloud.domain.hateoas.IngredientResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class IngredientResourceAssembler extends ResourceAssemblerSupport<Ingredient, IngredientResource> {
  public IngredientResourceAssembler() {
    super(IngredientController.class, IngredientResource.class);
  }

  @Override
  protected IngredientResource instantiateResource(Ingredient ingredient) {
    return new IngredientResource(ingredient);
  }

  @Override
  public IngredientResource toResource(Ingredient ingredient) {
    return createResourceWithId(ingredient.getId(), ingredient);
  }
}
