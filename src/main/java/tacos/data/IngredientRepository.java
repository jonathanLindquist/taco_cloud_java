package tacos.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tacos.domain.Ingredient;

import java.util.List;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, String> {
	List<Ingredient> findAll();

	Ingredient save(Ingredient ingredient);
}
