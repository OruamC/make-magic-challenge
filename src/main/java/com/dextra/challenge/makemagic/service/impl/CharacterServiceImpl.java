package com.dextra.challenge.makemagic.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dextra.challenge.makemagic.domains.Character;
import com.dextra.challenge.makemagic.domains.dto.CharacterRequestDTO;
import com.dextra.challenge.makemagic.domains.dto.CharacterResponseDTO;
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
	public CharacterResponseDTO createCharacter(CharacterRequestDTO dto) {
		if(!this.houseService.isAValidHouse(dto.getHouse())) {
			throw new EntityNotFoundException("House not found");
		}
		Character character = this.mapper.characterRequestToCharacter(dto);
		character = this.repository.save(character);
		return this.mapper.characterToCharacterResponseDTO(character);
	}

}
