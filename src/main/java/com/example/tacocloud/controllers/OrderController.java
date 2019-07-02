package com.example.tacocloud.controllers;

import com.example.tacocloud.domain.Order;
import com.example.tacocloud.repostory.OrderRepository;
import com.example.tacocloud.services.OrderMessagingService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/orders",
        produces = "application/json")
@CrossOrigin(origins = "*")
public class OrderController {

  private final OrderRepository repo;
  private final OrderMessagingService orderMessages;

  public OrderController(OrderRepository repo, OrderMessagingService orderMessages) {
    this.repo = repo;
    this.orderMessages = orderMessages;
  }

  @GetMapping(produces = "application/json")
  public Iterable<Order> allOrders() {
    return repo.findAll();
  }

  @PostMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public Order postOrder(@RequestBody Order order) {
    orderMessages.sendOrder(order);
    return repo.save(order);
  }

  @PutMapping(path = "/{orderId}", consumes = "application/json")
  public Order putOrder(@RequestBody Order order) {
    return repo.save(order);
  }

  @PatchMapping(path = "/{orderId}", consumes = "application/json")
  public Order patchOrder(@PathVariable("orderId") Long orderId,
                          @RequestBody Order patch) {

    Order order = repo.findById(orderId).get();
    if (patch.getName() != null) {
      order.setName(patch.getName());
    }
    if (patch.getStreet() != null) {
      order.setStreet(patch.getStreet());
    }
    if (patch.getCity() != null) {
      order.setCity(patch.getCity());
    }
    if (patch.getState() != null) {
      order.setState(patch.getState());
    }
    if (patch.getZip() != null) {
      order.setZip(patch.getState());
    }
    if (patch.getCcNumber() != null) {
      order.setCcNumber(patch.getCcNumber());
    }
    if (patch.getCcExpiration() != null) {
      order.setCcExpiration(patch.getCcExpiration());
    }
    if (patch.getCcCVV() != null) {
      order.setCcCVV(patch.getCcCVV());
    }
    return repo.save(order);
  }

  @DeleteMapping("/{orderId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteOrder(@PathVariable("orderId") Long orderId) {
    try {
      repo.deleteById(orderId);
    } catch (EmptyResultDataAccessException e) {
    }
  }
}
