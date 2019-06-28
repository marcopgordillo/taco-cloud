package com.example.tacocloud.repostory;

import com.example.tacocloud.domain.Taco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TacoRepository extends JpaRepository<Taco, Long> {
}
