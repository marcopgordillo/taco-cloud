package com.example.tacocloud.repostory;

import com.example.tacocloud.domain.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

  List<Order> findByZip(String deliveryZip);

  List<Order> readOrdersByZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);

  @Query("SELECT o from Order o where o.city=?1")
  List<Order> readOrdersDeliveredInSeattle(String city);
}
