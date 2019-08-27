package com.tacocloud.tacos.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.tacocloud.tacos.domain.Taco;

@Repository
public interface TacoRepository extends CrudRepository<Taco, Long> {

	Taco save(Taco taco);
}
