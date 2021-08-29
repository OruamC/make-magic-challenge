package com.dextra.challenge.makemagic.client;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.dextra.challenge.makemagic.domains.House;

@FeignClient(name= "houses", url = "${uri.houses}")
public interface HouseClient {

	@GetMapping("/potterApi/houses")
	Map<String, List<House>> getAllHouses(@RequestHeader("apiKey") String apiKey);
}
