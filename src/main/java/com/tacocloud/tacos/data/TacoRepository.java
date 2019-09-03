package com.tacocloud.tacos.data;

import com.tacocloud.tacos.domain.Taco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacoRepository extends CrudRepository<Taco, Long> {

	Taco save(Taco taco);
}
