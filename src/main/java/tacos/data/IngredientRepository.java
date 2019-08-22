package tacos.data;

import tacos.domain.Ingredient;

import java.util.List;

public interface IngredientRepository {
	List<Ingredient> findAll();

	Ingredient findOne(String id);

	Ingredient save(Ingredient ingredient);
}
