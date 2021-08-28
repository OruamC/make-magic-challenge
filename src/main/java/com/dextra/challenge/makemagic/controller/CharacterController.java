package com.dextra.challenge.makemagic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
