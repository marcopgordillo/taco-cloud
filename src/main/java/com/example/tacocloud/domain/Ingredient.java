package com.example.tacocloud.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class Ingredient implements Serializable {

  private static final long serialVersionUID = 1L;

  private final String id;
  private final String name;
  private final Type type;
}
