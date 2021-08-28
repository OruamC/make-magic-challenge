package com.dextra.challenge.makemagic.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.dextra.challenge.makemagic.domains.Character;
import com.dextra.challenge.makemagic.domains.dto.CharacterResponseDTO;
import com.dextra.challenge.makemagic.service.CharacterService;
import com.dextra.challenge.makemagic.util.CharacterCreator;

@ExtendWith(SpringExtension.class)
class CharacterControllerTest {

	@InjectMocks
	private CharacterController controller;

	@Mock
	private CharacterService service;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.controller).build();
		
		CharacterResponseDTO characterResponse = CharacterCreator.createCharacterResponseDTO();
		List<CharacterResponseDTO> list = new ArrayList<>();
		list.add(characterResponse);
		
		when(this.service.getAllCharacters()).thenReturn(list);
	}
	
	@Test
	public void getAllCharacters_returnAListOfCharacters_whenSucessful() throws Exception {
		Character savedCharacter = CharacterCreator.createCharacterDomain();
		
		ResultActions resultActions = this.mockMvc.perform(get("/api/characters")).andDo(MockMvcResultHandlers.print());

		resultActions.andExpect(status().isOk());
		resultActions.andExpect(jsonPath("$").isNotEmpty());
		resultActions.andExpect(jsonPath("$.[0].role", is(savedCharacter.getRole())));
		resultActions.andExpect(jsonPath("$.[0].name", is(savedCharacter.getName())));
		resultActions.andExpect(jsonPath("$.[0].school", is(savedCharacter.getSchool())));
		resultActions.andExpect(jsonPath("$.[0].house", is(savedCharacter.getHouse())));
		resultActions.andExpect(jsonPath("$.[0].patronus", is(savedCharacter.getPatronus())));
	}
	
	@Test
	public void create_returnCharacterResponse_whenSucessful() {
		
	}
}
