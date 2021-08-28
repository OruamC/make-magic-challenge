package com.dextra.challenge.makemagic.mapper;

import org.mapstruct.Mapper;

import com.dextra.challenge.makemagic.domains.Character;
import com.dextra.challenge.makemagic.domains.dto.CharacterResponseDTO;

@Mapper(componentModel = "spring")
public interface MapStructCharacterMapper {

	CharacterResponseDTO characterToCharacterResponseDTO(Character character);
}
