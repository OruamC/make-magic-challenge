package com.dextra.challenge.makemagic.mapper.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.dextra.challenge.makemagic.domains.Character;
import com.dextra.challenge.makemagic.domains.dto.CharacterResponseDTO;
import com.dextra.challenge.makemagic.util.CharacterCreator;

@ExtendWith(SpringExtension.class)
class MapStructCharacterMapperImplTest {

	@InjectMocks
	private MapStructCharacterMapperImpl mapper;

	@Test
	public void mapper_ReturnCharacterResponseDTO_whenRecevesCharacterDomain() {
		Character character = CharacterCreator.createCharacterDomain();
		
		CharacterResponseDTO dto = this.mapper.characterToCharacterResponseDTO(character);
		
		Assertions.assertThat(dto).isNotNull();
		Assertions.assertThat(dto.getRole()).isEqualTo(character.getRole());
		Assertions.assertThat(dto.getName()).isEqualTo(character.getName());
		Assertions.assertThat(dto.getSchool()).isEqualTo(character.getSchool());
		Assertions.assertThat(dto.getHouse()).isEqualTo(character.getHouse());
		Assertions.assertThat(dto.getPatronus()).isEqualTo(character.getPatronus());
	}
}
