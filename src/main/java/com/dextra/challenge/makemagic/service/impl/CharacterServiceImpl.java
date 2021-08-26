package com.dextra.challenge.makemagic.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dextra.challenge.makemagic.domains.dto.CharacterResponseDTO;
import com.dextra.challenge.makemagic.mapper.MapStructCharacterMapper;
import com.dextra.challenge.makemagic.repositories.CharacterRepository;
import com.dextra.challenge.makemagic.service.CharacterService;

@Service
public class CharacterServiceImpl implements CharacterService {
	
	private CharacterRepository repository;
	private MapStructCharacterMapper mapper;

	@Autowired
	public CharacterServiceImpl(CharacterRepository repository, MapStructCharacterMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public List<CharacterResponseDTO> getAllCharacters() {
		return this.repository.findAll().stream()
				.map(obj -> this.mapper.characterToCharacterResponseDTO(obj))
				.collect(Collectors.toList());
	}

}
