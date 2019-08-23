package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.domain.Ingredient;

import java.util.List;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
	List<Ingredient> findAll();

	Ingredient findOne(String id);

	Ingredient save(Ingredient ingredient);
}
