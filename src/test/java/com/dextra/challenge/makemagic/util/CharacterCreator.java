package com.dextra.challenge.makemagic.util;

import com.dextra.challenge.makemagic.domains.Character;
import com.dextra.challenge.makemagic.domains.dto.CharacterResponseDTO;

public class CharacterCreator {

	public static Character createCharacterDomain() {
		return Character.builder()
				.id(1L)
				.role("student")
				.name("Harry Potter")
				.school("Hogwarts")
				.house("House test")
				.patronus("stag")
				.build();
	}
	
	public static CharacterResponseDTO createCharacterResponseDTO() {
		return CharacterResponseDTO.builder()
				.role("student")
				.name("Harry Potter")
				.school("Hogwarts")
				.house("House test")
				.patronus("stag")
				.build();
	}
}
