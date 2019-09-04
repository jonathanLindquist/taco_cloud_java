package com.tacocloud.tacos.rest.controllers;

import com.tacocloud.tacos.data.TacoRepository;
import com.tacocloud.tacos.domain.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/design", produces = "application/json")
@CrossOrigin(origins="*")
public class RestDesignTacoController {
	private TacoRepository tacoRepository;

	@Autowired
	public RestDesignTacoController(TacoRepository tacoRepository) {
		this.tacoRepository = tacoRepository;
	}

//	@GetMapping("/recent")
//	public Iterable<Taco> recentTacos() {
//		PageRequest pageRequest = PageRequest.of(0, 12, Sort.by("createDate").descending());
//		return tacoRepository.findAll(pageRequest);
//	}

	@GetMapping("/{id}")
	public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
		Optional<Taco> optionalTaco = tacoRepository.findById(id);
//		return optionalTaco.isPresent() ? optionalTaco.get() : null;
		if (optionalTaco.isPresent()) {
			return new ResponseEntity<>(optionalTaco.get(), HttpStatus.OK);
		}

		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
}
