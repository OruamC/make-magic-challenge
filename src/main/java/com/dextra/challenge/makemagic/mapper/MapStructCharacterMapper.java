package com.dextra.challenge.makemagic.mapper;

import org.mapstruct.Mapper;

import com.dextra.challenge.makemagic.domains.Character;
import com.dextra.challenge.makemagic.domains.dto.CharacterRequestDTO;
import com.dextra.challenge.makemagic.domains.dto.CharacterResponseDTO;

/**
 * The Map Struct Character Mapper
 * 
 * <p>
 * This interface is responsible to define the methods needed to map the character objects.
 * </p>
 * 
 * @author oruam
 *
 */
@Mapper(componentModel = "spring")
public interface MapStructCharacterMapper {

	/**
	 * This method is responsible to map a character object to a character response dto object.
	 * 
	 * @param character is an object.
	 * @return a character response dto object.
	 */
	CharacterResponseDTO characterToCharacterResponseDTO(Character character);
	
	/**
	 * This method is responsible to map a character request dto to a character object.
	 *  
	 * @param dto character response dto object.
	 * @return a character object.
	 */
	Character characterRequestToCharacter(CharacterRequestDTO dto);
}
