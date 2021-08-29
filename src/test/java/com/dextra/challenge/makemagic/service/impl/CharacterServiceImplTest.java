package com.dextra.challenge.makemagic.service.impl;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.dextra.challenge.makemagic.domains.dto.CharacterRequestDTO;
import com.dextra.challenge.makemagic.domains.dto.CharacterResponseDTO;
import com.dextra.challenge.makemagic.exceptions.custom.ResourceNotFoundException;
import com.dextra.challenge.makemagic.mapper.MapStructCharacterMapper;
import com.dextra.challenge.makemagic.repositories.CharacterRepository;
import com.dextra.challenge.makemagic.service.HouseService;
import com.dextra.challenge.makemagic.util.CharacterCreator;

@ExtendWith(SpringExtension.class)
class CharacterServiceImplTest {

	@InjectMocks
	private CharacterServiceImpl service;
	
	@Mock
	private CharacterRepository repository;
	
	@Mock
	private HouseService houseService;
	
	@Mock
	private MapStructCharacterMapper mapper;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		
		Character savedCharacter = CharacterCreator.createCharacterDomain();
		Optional<Character> optionalCharacter = Optional.of(savedCharacter);
		CharacterResponseDTO responseDTO = CharacterCreator.createCharacterResponseDTO();
		
		List<Character> listOfCharacters = new ArrayList<>();
		listOfCharacters.add(savedCharacter);
		
		when(this.repository.findAll()).thenReturn(listOfCharacters);
		when(this.repository.findById(ArgumentMatchers.anyLong())).thenReturn(optionalCharacter);
		when(this.repository.save(ArgumentMatchers.any(Character.class))).thenReturn(savedCharacter);
		
		when(this.mapper.characterRequestToCharacter(ArgumentMatchers.any(CharacterRequestDTO.class))).thenReturn(savedCharacter);
		when(this.mapper.characterToCharacterResponseDTO(ArgumentMatchers.any(Character.class))).thenReturn(responseDTO);
		
		when(this.houseService.isAValidHouse(ArgumentMatchers.anyString())).thenReturn(Boolean.TRUE);
		
	}
	
	@Test
	public void getAll_returnAListOfCharacterResponseDTO_whenSucessful() {
		Character savedCharacter = CharacterCreator.createCharacterDomain();
		
		List<CharacterResponseDTO> resultListOfCharactersResponse = this.service.getAllCharacters();
		
		Assertions.assertThat(resultListOfCharactersResponse).isNotEmpty().isNotNull().hasSize(1);
		
		Assertions.assertThat(resultListOfCharactersResponse.get(0).getName()).isEqualTo(savedCharacter.getName());
		Assertions.assertThat(resultListOfCharactersResponse.get(0).getId()).isEqualTo(savedCharacter.getId());
		Assertions.assertThat(resultListOfCharactersResponse.get(0).getHouse()).isEqualTo(savedCharacter.getHouse());
		Assertions.assertThat(resultListOfCharactersResponse.get(0).getRole()).isEqualTo(savedCharacter.getRole());
		Assertions.assertThat(resultListOfCharactersResponse.get(0).getSchool()).isEqualTo(savedCharacter.getSchool());
		Assertions.assertThat(resultListOfCharactersResponse.get(0).getPatronus()).isEqualTo(savedCharacter.getPatronus());
	}
	
	@Test
	public void getByID_returnACharacterResponseDTO_whenSucessful() {
		Character savedCharacter = CharacterCreator.createCharacterDomain();
		
		CharacterResponseDTO response = this.service.getById(1L);
		
		Assertions.assertThat(response).isNotNull();
		
		Assertions.assertThat(response.getName()).isEqualTo(savedCharacter.getName());
		Assertions.assertThat(response.getId()).isEqualTo(savedCharacter.getId());
		Assertions.assertThat(response.getHouse()).isEqualTo(savedCharacter.getHouse());
		Assertions.assertThat(response.getRole()).isEqualTo(savedCharacter.getRole());
		Assertions.assertThat(response.getSchool()).isEqualTo(savedCharacter.getSchool());
		Assertions.assertThat(response.getPatronus()).isEqualTo(savedCharacter.getPatronus());
	}
	
	@Test
	public void getByID_throwsResourceNotFound_whenNotSucessful() {
		when(this.repository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
		
		Assertions.assertThatExceptionOfType(ResourceNotFoundException.class)
			.isThrownBy(() -> this.service.getById(2L));
	}

	@Test
	public void create_returnACharacterResponseDTO_whenHasValidHouseIDAndIsSucessful() {
		CharacterRequestDTO characterRequest = CharacterCreator.createCharacterRequest();
		
		CharacterResponseDTO characterResponse = this.service.createCharacter(characterRequest);
		
		Assertions.assertThat(characterResponse).isNotNull();
		Assertions.assertThat(characterResponse.getId()).isEqualTo(1L);
		Assertions.assertThat(characterResponse.getName()).isEqualTo(characterRequest.getName());
		Assertions.assertThat(characterResponse.getRole()).isEqualTo(characterRequest.getRole());
		Assertions.assertThat(characterResponse.getHouse()).isEqualTo(characterRequest.getHouse());
		Assertions.assertThat(characterResponse.getSchool()).isEqualTo(characterRequest.getSchool());
		Assertions.assertThat(characterResponse.getPatronus()).isEqualTo(characterRequest.getPatronus());
	}
	
	@Test
	public void create_throwException_whenHasInvalidHouseID() {
		CharacterRequestDTO characterRequest = CharacterCreator.createCharacterRequest();

		when(this.houseService.isAValidHouse(ArgumentMatchers.anyString())).thenReturn(Boolean.FALSE);
		
		Assertions.assertThatExceptionOfType(ResourceNotFoundException.class)
			.isThrownBy(() -> this.service.createCharacter(characterRequest));
	}
}
