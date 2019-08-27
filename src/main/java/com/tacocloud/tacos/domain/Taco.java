package com.tacocloud.tacos.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Taco {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Size(min = 5, message = "Name must be at least 5 characters long")
	private String name;

	@ManyToMany(targetEntity = Ingredient.class)
	@Size(min = 1, message = "You must choose at least 1 ingredient")
	private List<Ingredient> ingredients;

	private Date createDate;

	@PrePersist
	void createDate() {
		this.createDate = new Date();
	}
}
