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


/**
 * The controller for Character REST endpoints.
 * 
 * <p>
 * This class handle the CRUD operations for Characters from Harry Potter Universe, via HTTP actions.
 * </p>
 * 
 * *@author oruam
 *
 */
@Validated
@RestController
@RequestMapping(path = "/api/characters")
public class CharacterController {

	private CharacterService service;

	@Autowired
	public CharacterController(CharacterService service) {
		this.service = service;
	}
	
	/**
	 * Get all the characters saved on the database or return all the characters based on the house id.
	 * 
	 * Returns one of the following status code:
	 * 200: sucessfully return a list of Characters.
	 * 
	 * @param house past by request param to filter the results by this parameter.
	 * @return a list of Characters.
	 */
	@GetMapping
	@Operation(summary = "Get All Characters", description = "By default returns all saved characters.")
	public ResponseEntity<List<CharacterResponseDTO>> getAllCharacters(
			@Pattern(regexp = "^\\w{8}-\\w{4}-\\w{4}-\\w{4}-\\w{12}$", message = "Invalid House ID")
			@RequestParam(required = false, name = "house") String house) {
		return ResponseEntity.ok(this.service.getAllCharacters(house));
	}
	
	/**
	 * Get a character based on the id.
	 * 
	 * Returns one of the following status code:
	 * 200: sucessfully a Character.
	 * 404: unable to return a character, because didn't found any register with this id.
	 * 
	 * @param id past by path variable.
	 * @return a single character when sucessful.
	 */
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
	
	
	/**
	 * Create a new character object, given the data provided.
	 * 
	 * Returns one of the following status code:
	 * 201: sucessfully created a new character.
	 * 404: unable to create character, because didn't found a house by house id.  
	 * 
	 * @param dto a JSON representation of a character object. 
	 * @return the newly created character object.
	 */
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
	
	/**
	 * Replace the informations about one character in the database.
	 * 
	 * Returns one of the following status code:
	 * 200: sucessfully update the informations of the character.
	 * 404: unable to update the character, because didn't found any register with this id. 
	 * 
	 * @param dto a JSON representation of a character object. 
	 * @param id past by path variable.
	 * @return an updated character object.
	 */
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
	
	/**
	 * Remove a character from the database.
	 * 
	 * Returns one of the following status code:
	 * 204: sucessfully remove the informations of the character.
	 * 404: unable to update the character, because didn't found any register with this id. 
	 * 
	 * @param id past by path variable.
	 * @return an empty response when successful.
	 */
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
