package com.example.tacocloud.domain;

import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "tacos")
@RestResource(rel = "tacos", path = "tacos")
public class Taco implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private Date createdAt;

  @NotNull
  @Size(min = 5, message = "Name must be at least 5 characters long")
  @Column(nullable = false, length = 50)
  private String name;

  @ManyToMany(targetEntity = Ingredient.class)
  @Size(min = 1, message = "You must choose at least 1 ingredient")
  private List<Ingredient> ingredients = new ArrayList<>();

  @PrePersist
  void createdAt() {
    this.createdAt = new Date();
  }
}
