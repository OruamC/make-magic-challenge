package com.dextra.challenge.makemagic.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.dextra.challenge.makemagic.domains.Character;
import com.dextra.challenge.makemagic.domains.dto.CharacterRequestDTO;
import com.dextra.challenge.makemagic.domains.dto.CharacterResponseDTO;
import com.dextra.challenge.makemagic.exceptions.custom.ResourceNotFoundException;
import com.dextra.challenge.makemagic.mapper.MapStructCharacterMapper;
import com.dextra.challenge.makemagic.repositories.CharacterRepository;
import com.dextra.challenge.makemagic.service.CharacterService;
import com.dextra.challenge.makemagic.service.HouseService;

@Service
public class CharacterServiceImpl implements CharacterService {

	private CharacterRepository repository;
	private MapStructCharacterMapper mapper;
	private HouseService houseService;

	@Autowired
	public CharacterServiceImpl(CharacterRepository repository, MapStructCharacterMapper mapper,
			HouseService houseService) {
		this.repository = repository;
		this.mapper = mapper;
		this.houseService = houseService;
	}

	@Override
	public List<CharacterResponseDTO> getAllCharacters() {
		return this.repository.findAll().stream()
				.map(obj -> this.mapper.characterToCharacterResponseDTO(obj))
				.collect(Collectors.toList());
	}

	@Override
	public CharacterResponseDTO getById(Long id) {
		Character character = this.repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Character not found"));
		return this.mapper.characterToCharacterResponseDTO(character);
	}

	@Override
	public CharacterResponseDTO createCharacter(CharacterRequestDTO dto) {
		checkingHouseId(dto.getHouse());
		Character character = this.mapper.characterRequestToCharacter(dto);
		character = this.repository.save(character);
		return this.mapper.characterToCharacterResponseDTO(character);
	}

	@Override
	public CharacterResponseDTO updateCharacter(CharacterRequestDTO dto, Long id) {
		this.getById(id);
		checkingHouseId(dto.getHouse());
		Character characterToUpdate = this.mapper.characterRequestToCharacter(dto);
		characterToUpdate.setId(id);
		Character updatedCharacter = this.repository.save(characterToUpdate);
		return this.mapper.characterToCharacterResponseDTO(updatedCharacter);
	}
	
	@Override
	public void deleteCharacter(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}
	
	private void checkingHouseId(String houseId) {
		if(!this.houseService.isAValidHouse(houseId)) {
			throw new ResourceNotFoundException("House not found");
		}
	}
}
