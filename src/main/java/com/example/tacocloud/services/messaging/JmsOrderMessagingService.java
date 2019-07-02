package com.example.tacocloud.services.messaging;

import com.example.tacocloud.domain.Order;
import com.example.tacocloud.services.OrderMessagingService;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;

@Service
public class JmsOrderMessagingService implements OrderMessagingService {

  private final JmsTemplate jms;

  public JmsOrderMessagingService(JmsTemplate jms) {
    this.jms = jms;
  }

  /*@Override
  public void sendOrder(Order order) {
    *//*jms.send(new MessageCreator() {
               @Override
               public Message createMessage(Session session)
                       throws JMSException {
                 return session.createObjectMessage(order);
               }
             }
    );*//*
//    jms.send(session -> session.createObjectMessage(order));
    *//*jms.send(
            orderQueue,
            session -> session.createObjectMessage(order));*//*
    jms.send(
            "tacocloud.order.queue",
            session -> session.createObjectMessage(order));
  }*/

  /*@Override
  public void sendOrder(Order order) {
    jms.convertAndSend("tacocloud.order.queue", order);
  }*/

  @Override
  public void sendOrder(Order order) {
    jms.convertAndSend("tacocloud.order.queue", order,
            this::addOrderSource);
  }

  private Message addOrderSource(Message message) throws JMSException {
    message.setStringProperty("X_ORDER_SOURCE", "WEB");
    return message;
  }
}
