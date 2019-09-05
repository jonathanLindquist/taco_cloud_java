package com.tacocloud.tacos.data;

import com.tacocloud.tacos.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, String> {
	List<Ingredient> findAll();

	Ingredient save(Ingredient ingredient);
}
