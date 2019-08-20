package tacos.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Taco {
	private String name;

	private List<String> ingredients;
}
