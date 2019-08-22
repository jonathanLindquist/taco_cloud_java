package tacos.data.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import tacos.data.TacoRepository;
import tacos.domain.Ingredient;
import tacos.domain.Taco;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

@Repository
public class JdbcTacoRepository implements TacoRepository {
	private JdbcTemplate jdbc;

	@Autowired
	public JdbcTacoRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public Taco save(Taco taco) {
		long tacoId = saveTacoInfo(taco);
		taco.setId(tacoId);
		taco.getIngredients().forEach(i -> saveIngredientTaco(i, tacoId));
		return taco;
	}

	private long saveTacoInfo(Taco taco) {
		taco.setCreateDate(new Date());
		PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
				"insert into Taco (name, createDate) values (?, ?)", Types.VARCHAR, Types.TIMESTAMP
		);

		pscf.setReturnGeneratedKeys(true);

		PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
				Arrays.asList(
						taco.getName(),
						new Timestamp(taco.getCreateDate().getTime())
				)
		);

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbc.update(psc, keyHolder);

		return keyHolder.getKey().longValue();
	}

	private void saveIngredientTaco(String ingredient, long tacoId) {
		jdbc.update("insert into Taco_Ingredients (taco, ingredient) values (?,?)", tacoId, ingredient);
	}

	private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException {
		return new Ingredient(
				rs.getString("id"),
				rs.getString("name"),
				Ingredient.Type.valueOf(rs.getString("type"))
		);
	}
}
