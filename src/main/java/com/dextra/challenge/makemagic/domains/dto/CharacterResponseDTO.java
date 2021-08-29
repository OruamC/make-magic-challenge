package com.dextra.challenge.makemagic.domains.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CharacterResponseDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Schema(description = "This is the character's id", example = "1")
	@JsonProperty("id")
	private Long id;
	
	@Schema(description = "This is the character's name", example = "Harry Potter")
	@JsonProperty("name")
	private String name;
	
	@Schema(description = "This is the character's role", example = "study")
	@JsonProperty("role")
	private String role;
	
	@Schema(description = "This is the character's school", example = "Hogwarts School of Witchcraft and Wizardry")
	@JsonProperty("school")
	private String school;

	@Schema(description = "This is the character's house ID", example = "1760529f-6d51-4cb1-bcb1-25087fce5bde")
	@JsonProperty("house")
	private String house;
	
	@Schema(description = "This is the character's patronus", example = "stag")
	@JsonProperty("patronus")
	private String patronus;
}
