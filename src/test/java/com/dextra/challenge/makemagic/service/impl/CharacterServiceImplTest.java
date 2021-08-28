package com.dextra.challenge.makemagic.service.impl;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.dextra.challenge.makemagic.domains.Character;
import com.dextra.challenge.makemagic.domains.dto.CharacterResponseDTO;
import com.dextra.challenge.makemagic.mapper.MapStructCharacterMapper;
import com.dextra.challenge.makemagic.repositories.CharacterRepository;
import com.dextra.challenge.makemagic.util.CharacterCreator;

@ExtendWith(SpringExtension.class)
class CharacterServiceImplTest {

	@InjectMocks
	private CharacterServiceImpl service;
	
	@Mock
	private CharacterRepository repository;
	
	@Mock
	private MapStructCharacterMapper mapper;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		
		Character savedCharacter = CharacterCreator.createCharacterDomain();
		CharacterResponseDTO responseDTO = CharacterCreator.createCharacterResponseDTO();
		
		List<Character> listOfCharacters = new ArrayList<>();
		listOfCharacters.add(savedCharacter);
		
		when(this.repository.findAll()).thenReturn(listOfCharacters);
		when(this.mapper.characterToCharacterResponseDTO(ArgumentMatchers.any(Character.class))).thenReturn(responseDTO);
		
	}
	
	@Test
	public void getAll_returnAListOfCharacterResponseDTO_whenSucessful() {
		Character savedCharacter = CharacterCreator.createCharacterDomain();
		
		List<CharacterResponseDTO> resultListOfCharactersResponse = this.service.getAllCharacters();
		
		Assertions.assertThat(resultListOfCharactersResponse).isNotEmpty().isNotNull().hasSize(1);
		
		Assertions.assertThat(resultListOfCharactersResponse.get(0).getName()).isEqualTo(savedCharacter.getName());
	}

	@Test
	public void create_returnACharacterResponseDTO_whenHasValidHouseIDAndIsSucessful() {
		
	}
}
