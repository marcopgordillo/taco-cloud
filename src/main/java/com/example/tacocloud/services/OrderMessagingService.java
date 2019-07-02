package com.example.tacocloud.services;

import com.example.tacocloud.domain.Order;

public interface OrderMessagingService {

  void sendOrder(Order order);
}
