package com.tacocloud.tacos.data;

import com.tacocloud.tacos.domain.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

	Order save(Order order);
}
