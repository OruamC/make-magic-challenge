package com.dextra.challenge.makemagic.domains.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CharacterResponseDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("role")
	private String role;
	
	@JsonProperty("school")
	private String school;

	@JsonProperty("house")
	private String house;
	
	@JsonProperty("patronus")
	private String patronus;
}
