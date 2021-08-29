package com.dextra.challenge.makemagic.service.impl;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.dextra.challenge.makemagic.client.HouseClient;
import com.dextra.challenge.makemagic.domains.House;
import com.dextra.challenge.makemagic.util.HouseCreator;

@ExtendWith(SpringExtension.class)
class HouseServiceImplTest {

	@InjectMocks
	private HouseServiceImpl service;
	
	@Mock
	private HouseClient client;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		
		ReflectionTestUtils.setField(service, "apiKey", "api.uri");
		
		House createHouse = HouseCreator.createHouse();
		List<House> allHouses = new ArrayList<>();
		allHouses.add(createHouse);
		Map<String, List<House>> allMapedHouses = new HashMap<>();
		allMapedHouses.put("houses", allHouses);
		
		when(this.client.getAllHouses(ArgumentMatchers.anyString())).thenReturn(allMapedHouses);
	}
	
	@Test
	public void isAValidHouse_ReturnsTrue_whenIsAValidId() {
		String houseId = "Valid ID";
		Boolean aValidHouse = this.service.isAValidHouse(houseId);
		
		Assertions.assertTrue(aValidHouse);
	}
	
	@Test
	public void isAValidHouse_ReturnsFalse_whenIsAInvalidId() {
		String houseId = "Invalid ID";
		Boolean aValidHouse = this.service.isAValidHouse(houseId);
		
		Assertions.assertFalse(aValidHouse);
	}


}
