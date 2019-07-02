package com.example.tacocloud.services.messaging;

import com.example.tacocloud.domain.Order;
import com.example.tacocloud.services.OrderMessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaOrderMessagingService implements OrderMessagingService {

  private KafkaTemplate<String, Order> kafkaTemplate;

  @Autowired
  public KafkaOrderMessagingService(KafkaTemplate<String, Order> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  @Override
  public void sendOrder(Order order) {
    kafkaTemplate.sendDefault(order);
  }
}
