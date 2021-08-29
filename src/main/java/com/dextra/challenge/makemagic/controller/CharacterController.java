package com.dextra.challenge.makemagic.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dextra.challenge.makemagic.domains.dto.CharacterRequestDTO;
import com.dextra.challenge.makemagic.domains.dto.CharacterResponseDTO;
import com.dextra.challenge.makemagic.service.CharacterService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Validated
@RestController
@RequestMapping(path = "/api/characters")
public class CharacterController {

	private CharacterService service;

	@Autowired
	public CharacterController(CharacterService service) {
		this.service = service;
	}
	
	@GetMapping
	@Operation(summary = "Get All Characters", description = "By default returns all saved characters.")
	public ResponseEntity<List<CharacterResponseDTO>> getAllCharacters(
			@Pattern(regexp = "^\\w{8}-\\w{4}-\\w{4}-\\w{4}-\\w{12}$", message = "Invalid House ID")
			@RequestParam(required = false, name = "house") String house) {
		return ResponseEntity.ok(this.service.getAllCharacters(house));
	}
	
	@GetMapping(path = "/{id}")
	@Operation(summary = "Get a Character based on ID", 
		description = "Return a character using the value pass by path variable.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucessful Operation"),
			@ApiResponse(responseCode = "404", description = "When character id does not exist in the Database", content = @Content)
	})
	public ResponseEntity<CharacterResponseDTO> getById(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok(this.service.getById(id));
	}
	
	@PostMapping
	@Operation(summary = "Save a Character on BD", description = "Saves a character if the house id is right.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Sucessful Operation"),
			@ApiResponse(responseCode = "404", description = "When character id does not exist in the Database", content = @Content)
	})
	public ResponseEntity<CharacterResponseDTO> createCharacter(@RequestBody @Valid CharacterRequestDTO dto) {
		CharacterResponseDTO savedCharacter = this.service.createCharacter(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedCharacter.getId()).toUri();
		return ResponseEntity.created(uri).body(savedCharacter);
	}
	
	@PutMapping(path = "/{id}")
	@Operation(summary = "Updates values of a Character", description = "Replaces the values of a character if the id exists.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucessful Operation"),
			@ApiResponse(responseCode = "404", description = "When character id does not exist in the Database", content = @Content)
	})
	public ResponseEntity<CharacterResponseDTO> updateCharacter(@RequestBody @Valid CharacterRequestDTO dto,
			@PathVariable(name = "id") Long id) {
		CharacterResponseDTO updatedCharacter = this.service.updateCharacter(dto, id);
		return ResponseEntity.ok(updatedCharacter);
	}
	
	@DeleteMapping(path = "/{id}")
	@Operation(summary = "Deletes a character", description = "Deletes a character if the id exists.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Sucessful Operation"),
			@ApiResponse(responseCode = "404", description = "When character id does not exist in the Database", content = @Content)
	})
	public ResponseEntity<Void> deleteCharacter(@PathVariable(name = "id") Long id) {
		this.service.deleteCharacter(id);
		return ResponseEntity.noContent().build();
	}
	
}
