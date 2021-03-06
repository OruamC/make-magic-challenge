package com.dextra.challenge.makemagic.util;

import com.dextra.challenge.makemagic.domains.Character;
import com.dextra.challenge.makemagic.domains.dto.CharacterRequestDTO;
import com.dextra.challenge.makemagic.domains.dto.CharacterResponseDTO;

public class CharacterCreator {

	public static Character createCharacterDomain() {
		return Character.builder()
				.id(1L)
				.role("student")
				.name("Harry Potter")
				.school("Hogwarts")
				.house("1760529f-6d51-4cb1-bcb1-25087fce5bde")
				.patronus("stag")
				.build();
	}
	
	public static CharacterResponseDTO createCharacterResponseDTO() {
		return CharacterResponseDTO.builder()
				.id(1L)
				.role("student")
				.name("Harry Potter")
				.school("Hogwarts")
				.house("1760529f-6d51-4cb1-bcb1-25087fce5bde")
				.patronus("stag")
				.build();
	}
	
	public static CharacterRequestDTO createCharacterRequest() {
		return CharacterRequestDTO.builder()
				.role("student")
				.name("Harry Potter")
				.school("Hogwarts")
				.house("1760529f-6d51-4cb1-bcb1-25087fce5bde")
				.patronus("stag")
				.build();
	}
}
