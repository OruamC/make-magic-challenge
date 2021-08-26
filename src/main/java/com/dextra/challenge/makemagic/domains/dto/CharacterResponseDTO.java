package com.dextra.challenge.makemagic.domains.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CharacterResponseDTO {

	private String name;
	private String role;
	private String school;
	private String house;
	private String patronus;
}
