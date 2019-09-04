package com.tacocloud.tacos.data;

import com.tacocloud.tacos.domain.Order;
import com.tacocloud.tacos.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

	Order save(Order order);

	List<Order> findByUserOrderByCreateDateDesc(User user, Pageable pageable);
}
