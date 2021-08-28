package com.dextra.challenge.makemagic.mapper.impl;

import org.springframework.stereotype.Component;

import com.dextra.challenge.makemagic.domains.Character;
import com.dextra.challenge.makemagic.domains.dto.CharacterRequestDTO;
import com.dextra.challenge.makemagic.domains.dto.CharacterResponseDTO;
import com.dextra.challenge.makemagic.mapper.MapStructCharacterMapper;

@Component
public class MapStructCharacterMapperImpl implements MapStructCharacterMapper {

	@Override
	public CharacterResponseDTO characterToCharacterResponseDTO(Character character) {
		return CharacterResponseDTO.builder()
				.id(character.getId())
				.name(character.getName())
				.role(character.getRole())
				.school(character.getSchool())
				.house(character.getHouse())
				.patronus(character.getPatronus())
				.build();
	}

	@Override
	public Character characterRequestToCharacter(CharacterRequestDTO dto) {
		return Character.builder()
				.name(dto.getName())
				.role(dto.getRole())
				.school(dto.getSchool())
				.house(dto.getHouse())
				.patronus(dto.getPatronus())
				.build();
	}

	

}
