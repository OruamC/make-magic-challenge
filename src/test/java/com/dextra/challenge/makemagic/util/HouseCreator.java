package com.dextra.challenge.makemagic.util;

import com.dextra.challenge.makemagic.domains.House;

public class HouseCreator {

	public static House createHouse() {
		return House.builder()
				.id("Valid ID")
				.name("Test name")
				.build();
	}
}
