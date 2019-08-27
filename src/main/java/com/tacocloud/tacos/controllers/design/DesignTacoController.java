package com.tacocloud.tacos.controllers.design;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import com.tacocloud.tacos.data.IngredientRepository;
import com.tacocloud.tacos.data.TacoRepository;
import com.tacocloud.tacos.domain.Ingredient;
import com.tacocloud.tacos.domain.Order;
import com.tacocloud.tacos.domain.Taco;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

	private final IngredientRepository ingredientRepository;
	private final TacoRepository tacoRepository;

	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository) {
		this.ingredientRepository = ingredientRepository;
		this.tacoRepository = tacoRepository;
	}

	@ModelAttribute("order")
	public Order order() {
		return new Order();
	}

	@ModelAttribute("taco")
	public Taco taco() {
		return new Taco();
	}

	@GetMapping
	public String showDesignForm(Model model) {
		List<Ingredient> ingredients = ingredientRepository.findAll();

		Ingredient.Type[] types = Ingredient.Type.values();
		for (Ingredient.Type type: types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}

		model.addAttribute("design", new Taco());

		return "design/design";
	}

	@PostMapping
	public String processDesign(@Valid Taco taco, Errors errors, @ModelAttribute Order order) {
		if (errors.hasErrors()) {
			return "redirect:/design";
		}
		//save taco design, will do in chapter 3
		log.info("Processing Design " + taco);
		tacoRepository.save(taco);
		order.addTaco(taco);
		log.info("Taco " + taco.getName() + " saved to System");

		return "redirect:/order/current";
	}

	private List<Ingredient> filterByType(List<Ingredient> list, Ingredient.Type type) {
		return list.stream().filter(ingredient -> ingredient.getType().equals(type)).collect(Collectors.toList());
	}
}
