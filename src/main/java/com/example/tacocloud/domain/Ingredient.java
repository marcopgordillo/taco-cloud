package com.example.tacocloud.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@RequiredArgsConstructor
@Table(name = "ingredients")
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
public class Ingredient implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(length = 5)
  private final String id;

  @Column(nullable = false, length = 25)
  private final String name;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 10)
  private final Type type;
}
