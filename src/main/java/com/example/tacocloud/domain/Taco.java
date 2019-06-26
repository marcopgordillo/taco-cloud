package com.example.tacocloud.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Taco implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private Date createdAt;

  @NotNull
  @Size(min = 5, message = "Name must be at least 5 characters long")
  private String name;

  @Size(min = 1, message = "You must choose at least 1 ingredient")
  private List<Ingredient> ingredients = new ArrayList<>();
}
