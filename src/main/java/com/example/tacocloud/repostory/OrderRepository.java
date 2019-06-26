package com.example.tacocloud.repostory;

import com.example.tacocloud.domain.Order;

public interface OrderRepository {
  Order save(Order order);
}
