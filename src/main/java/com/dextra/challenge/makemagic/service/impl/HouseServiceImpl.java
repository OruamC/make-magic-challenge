package com.dextra.challenge.makemagic.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dextra.challenge.makemagic.client.HouseClient;
import com.dextra.challenge.makemagic.domains.House;
import com.dextra.challenge.makemagic.service.HouseService;

@Service
public class HouseServiceImpl implements HouseService {
	
	private static final String HOUSES_KEY = "houses";

	@Value("${secret-key}")
	private String apiKey;
	
	private HouseClient client;
	
	@Autowired
	public HouseServiceImpl(HouseClient client) {
		this.client = client;
	}

	@Override
	public Boolean isAValidHouse(String houseId) {
		Map<String, List<House>> allHouses = getAllHouses();
		Boolean isValid = allHouses.get(HOUSES_KEY).stream()
			.anyMatch(obj -> obj.getId().equals(houseId));
		return isValid;
	}

	public Map<String, List<House>> getAllHouses() {
		return this.client.getAllHouses(apiKey);
	}

}
