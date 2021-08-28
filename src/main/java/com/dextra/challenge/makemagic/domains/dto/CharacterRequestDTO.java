package com.dextra.challenge.makemagic.domains.dto;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CharacterRequestDTO {

	@NotBlank
	private String name;
	
	@NotBlank
	private String role;
	
	@NotBlank
	private String school;
	
	@NotBlank
	private String house;
	
	@NotBlank
	private String patronus;
}
