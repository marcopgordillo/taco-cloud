package com.example.tacocloud.controllers;

import com.example.tacocloud.configuration.OrderProps;
import com.example.tacocloud.domain.Taco;
import com.example.tacocloud.repostory.TacoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static java.util.Objects.isNull;

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

  @PostMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public Taco postTaco(@RequestBody Taco taco) {
    return tacoRepo.save(taco);
  }

  @PutMapping("/{tacoId}")
  public ResponseEntity<Taco> putTaco(@PathVariable("tacoId") Long tacoId, @RequestBody Taco taco) {
    Optional<Taco> optTaco = tacoRepo.findById(tacoId);

    if (optTaco.isPresent()) {
      tacoRepo.save(taco);
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
  }

  @PatchMapping(path = "/{tacoId}", consumes = "application/json")
  public ResponseEntity<Taco> patchTaco(@PathVariable("tacoId") Long tacoId, @RequestBody Taco tacoPatch) {
    log.info(tacoPatch.toString());
    Optional<Taco> optTaco = tacoRepo.findById(tacoId);
    if (optTaco.isPresent()) {

      Taco taco = optTaco.get();

      if (!isNull(tacoPatch.getCreatedAt())) {
        taco.setCreatedAt(tacoPatch.getCreatedAt());
      }
      if (!isNull(tacoPatch.getName())) {
        taco.setName(tacoPatch.getName());
      }
      if (!isNull(tacoPatch.getIngredients()) && !tacoPatch.getIngredients().isEmpty()) {
        taco.setIngredients(tacoPatch.getIngredients());
      }

      tacoRepo.save(taco);
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("/{tacoId}")
  public ResponseEntity deleteTaco(@PathVariable("tacoId") Long tacoId) {
    try {
      tacoRepo.deleteById(tacoId);
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    } catch (EmptyResultDataAccessException e) {
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
  }
}
