package com.dextra.challenge.makemagic.domains;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class House implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String headOfHouse;
	private List<String> values;
	private List<String> colors;
	private String school;
	private String mascot;
	private String houseGhost;
	private String founder;

}
