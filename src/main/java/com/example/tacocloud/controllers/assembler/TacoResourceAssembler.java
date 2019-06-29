package com.example.tacocloud.controllers.assembler;

import com.example.tacocloud.controllers.RecentTacosController;
import com.example.tacocloud.domain.Taco;
import com.example.tacocloud.domain.hateoas.TacoResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class TacoResourceAssembler extends ResourceAssemblerSupport<Taco, TacoResource> {

  public TacoResourceAssembler() {
    super(RecentTacosController.class, TacoResource.class);
  }

  @Override
  protected TacoResource instantiateResource(Taco taco) {
    return new TacoResource(taco);
  }

  @Override
  public TacoResource toResource(Taco taco) {
    return createResourceWithId(taco.getId(), taco);
  }
}
