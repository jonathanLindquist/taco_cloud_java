package tacos.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tacos.domain.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

	Order save(Order order);
}
