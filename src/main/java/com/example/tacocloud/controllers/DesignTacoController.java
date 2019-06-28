package com.example.tacocloud.controllers;

import com.example.tacocloud.configuration.OrderProps;
import com.example.tacocloud.domain.Taco;
import com.example.tacocloud.repostory.TacoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(path = "/design", produces = {"application/json", "text/xml"})
@CrossOrigin(origins = "*")
public class DesignTacoController {

  private final TacoRepository tacoRepo;
  private final OrderProps orderProps;

  public DesignTacoController(TacoRepository tacoRepo, OrderProps orderProps) {
    this.tacoRepo = tacoRepo;
    this.orderProps = orderProps;
  }

  @GetMapping("/recent")
  public Iterable<Taco> recentTacos() {
    PageRequest page = PageRequest.of(0, orderProps.getPageSize(), Sort.by("createdAt").descending());

    return tacoRepo.findAll(page).getContent();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
    Optional<Taco> optTaco = tacoRepo.findById(id);

    return optTaco
            .map(taco -> new ResponseEntity<>(taco, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
  }
}
