package com.dextra.challenge.makemagic.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dextra.challenge.makemagic.domains.dto.CharacterRequestDTO;
import com.dextra.challenge.makemagic.domains.dto.CharacterResponseDTO;
import com.dextra.challenge.makemagic.service.CharacterService;

@Controller
@RequestMapping(value = "/api/characters")
public class CharacterController {

	private CharacterService service;

	@Autowired
	public CharacterController(CharacterService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<CharacterResponseDTO>> getAllCharacters() {
		return ResponseEntity.ok(this.service.getAllCharacters());
	}
	
	@PostMapping
	public ResponseEntity<CharacterResponseDTO> createCharacter(@RequestBody @Valid CharacterRequestDTO dto) {
		CharacterResponseDTO savedCharacter = this.service.createCharacter(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedCharacter.getId()).toUri();
		return ResponseEntity.created(uri).body(savedCharacter);
	}
}
