package com.dextra.challenge.makemagic.domains.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CharacterRequestDTO {

	@NotBlank(message = "Must not be blank")
	@JsonProperty("name")
	private String name;
	
	@NotBlank(message = "Must not be blank")
	@JsonProperty("role")
	private String role;
	
	@NotBlank(message = "Must not be blank")
	@JsonProperty("school")
	private String school;

	@NotBlank(message = "Must not be blank")
	@JsonProperty("house")
	private String house;
	
	@NotBlank(message = "Must not be blank")
	@JsonProperty("patronus")
	private String patronus;
}
