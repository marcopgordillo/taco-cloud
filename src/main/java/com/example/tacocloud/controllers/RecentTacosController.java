package com.example.tacocloud.controllers;

import com.example.tacocloud.configuration.OrderProps;
import com.example.tacocloud.controllers.assembler.TacoResourceAssembler;
import com.example.tacocloud.domain.Taco;
import com.example.tacocloud.domain.hateoas.TacoResource;
import com.example.tacocloud.repostory.TacoRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RepositoryRestController
@RequestMapping("tacos")
public class RecentTacosController {
  private final TacoRepository tacoRepo;
  private final OrderProps orderProps;

  public RecentTacosController(TacoRepository tacoRepo, OrderProps orderProps) {
    this.tacoRepo = tacoRepo;
    this.orderProps = orderProps;
  }

  @GetMapping(path = "/recent", produces = "application/hal+json")
  public ResponseEntity<Resources<TacoResource>> recentTacos() {
    PageRequest page = PageRequest.of(0, orderProps.getPageSize(), Sort.by("createdAt").descending());
    List<Taco> tacos = tacoRepo.findAll(page).getContent();
    List<TacoResource> tacoResources = new TacoResourceAssembler().toResources(tacos);

    Resources<TacoResource> recentResources = new Resources<>(tacoResources);
    recentResources.add(
            linkTo(methodOn(RecentTacosController.class).recentTacos())
                    .withRel("recents"));

    return new ResponseEntity<>(recentResources, HttpStatus.OK);
  }
}
