package com.dextra.challenge.makemagic.service;

import java.util.List;

import com.dextra.challenge.makemagic.domains.dto.CharacterRequestDTO;
import com.dextra.challenge.makemagic.domains.dto.CharacterResponseDTO;

public interface CharacterService {

	List<CharacterResponseDTO> getAllCharacters();
	
	CharacterResponseDTO createCharacter(CharacterRequestDTO dto);
	
	CharacterResponseDTO updateCharacter(CharacterRequestDTO dto);
}
