package com.example.tacocloud.services;

import com.example.tacocloud.domain.Order;

public interface OrderMessagingService {

  public void sendOrder(Order order);
}
