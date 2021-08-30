package com.dextra.challenge.makemagic.service;

import java.util.List;

import com.dextra.challenge.makemagic.domains.dto.CharacterRequestDTO;
import com.dextra.challenge.makemagic.domains.dto.CharacterResponseDTO;

/**
 * The Character Service.
 * 
 *  <p>
 *  This interface define the methods responsible to CRUD operations and their responses.
 *  </p>
 * 
 * @author oruam
 *
 */
public interface CharacterService {

	/**
	 * Responsible for getting all characters or all characters based on the house id
	 * 
	 * @param house is used to recovery all character with this field value.
	 * @return a list of characters dto objects.
	 */
	List<CharacterResponseDTO> getAllCharacters(String house);
	
	/**
	 * Responsible for getting the character based on his database id.
	 * 
	 * @param id used to filter the search on the database.
	 * @return a character dto object.
	 */
	CharacterResponseDTO getById(Long id);
	
	/**
	 * Responsible for create a character object on the database.
	 * 
	 * @param dto object that represents the character domain.
	 * @return a character dto object.
	 */
	CharacterResponseDTO createCharacter(CharacterRequestDTO dto);
	
	/**
	 * Responsible for update de character information based on his database id.
	 * 
	 * @param dto object that represents the character domain.
	 * @param id value to search the registry on the database.
	 * @return a character dto object.
	 */
	CharacterResponseDTO updateCharacter(CharacterRequestDTO dto, Long id);
	
	/**
	 * Responsible for remove the registry on the database. 
	 * 
	 * @param id id value to search the registry on the database.
	 */
	void deleteCharacter(Long id);
}
