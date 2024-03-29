package com.example.tacocloud.domain;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Taco_Order")
public class Order implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private Date placedAt;

  @NotBlank(message = "Name is required")
  @Column(nullable = false, length = 50)
  private String name;

  @NotBlank(message = "Street is required")
  @Column(nullable = false, length = 50)
  private String street;

  @NotBlank(message = "City is required")
  @Column(nullable = false, length = 50)
  private String city;

  @NotBlank(message = "State is required")
  @Size(min = 2, max = 2, message = "State is 2 characters long")
  @Column(nullable = false, length = 2)
  private String state;

  @NotBlank(message = "Zip code is required")
  @Column(nullable = false, length = 10)
  private String zip;

  @CreditCardNumber(message = "Not a valid credit card number")
  @Column(nullable = false, length = 16)
  private String ccNumber;

  @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
          message = "Must be formatted MM/YY")
  @Column(nullable = false, length = 5)
  private String ccExpiration;

  @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
  @Column(nullable = false, length = 3)
  private String ccCVV;

  @ManyToMany(targetEntity = Taco.class)
  private List<Taco> tacos = new ArrayList<>();

  @ManyToOne
  private User user;

  public void addDesign(Taco design) {
    this.tacos.add(design);
  }

  @PrePersist
  void placedAt() {
    this.placedAt = new Date();
  }
}
