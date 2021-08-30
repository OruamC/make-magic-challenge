package com.dextra.challenge.makemagic.service;

/**
 * The House Service.
 * 
 * <p>
 * This interface define the methods responsible for validating the house id.
 * </p>
 * 
 * @author oruam
 *
 */
public interface HouseService {

	/**
	 * Responsible for search the id of the four houses and validate the passed parameter.
	 * 
	 * @param houseId used to validate.
	 * @return a boolean value.
	 */
	Boolean isAValidHouse(String houseId);
}
