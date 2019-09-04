package com.tacocloud.tacos.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
@Entity
public class Ingredient {

	@Id
	private final String id;
	private final String name;

	//necessary annotations for Hibernate to map an Enum to a String/Varchar in the DB (defaults to INTEGER)
	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private final Type type;

	public static enum Type {
		WRAP, PROTEIN, VEGGIE, CHEESE, SAUCE
	}

}
