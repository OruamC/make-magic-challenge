package com.dextra.challenge.makemagic.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.dextra.challenge.makemagic.domains.Character;
import com.dextra.challenge.makemagic.domains.dto.CharacterRequestDTO;
import com.dextra.challenge.makemagic.domains.dto.CharacterResponseDTO;
import com.dextra.challenge.makemagic.exceptions.ExceptionHandlerController;
import com.dextra.challenge.makemagic.exceptions.custom.ResourceNotFoundException;
import com.dextra.challenge.makemagic.service.CharacterService;
import com.dextra.challenge.makemagic.util.CharacterCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

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
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.controller)
				.setControllerAdvice(new ExceptionHandlerController()).build();
		
		CharacterResponseDTO characterResponse = CharacterCreator.createCharacterResponseDTO();
		List<CharacterResponseDTO> list = new ArrayList<>();
		list.add(characterResponse);
		
		when(this.service.getAllCharacters(ArgumentMatchers.anyString())).thenReturn(list);
		when(this.service.getAllCharacters(null)).thenReturn(list);
		when(this.service.getById(1L)).thenReturn(characterResponse);
		when(this.service.createCharacter(ArgumentMatchers.any(CharacterRequestDTO.class)))
			.thenReturn(characterResponse);
		when(this.service.updateCharacter(ArgumentMatchers.any(CharacterRequestDTO.class), ArgumentMatchers.anyLong()))
			.thenReturn(characterResponse);
		doNothing().when(this.service).deleteCharacter(ArgumentMatchers.anyLong());
	}
	
	@Test
	public void getAllCharacters_returnAListOfCharacters_whenSucessful() throws Exception {
		Character savedCharacter = CharacterCreator.createCharacterDomain();
		
		ResultActions resultActions = this.mockMvc.perform(get("/api/characters"))
				.andDo(MockMvcResultHandlers.print());

		resultActions.andExpect(status().isOk());
		resultActions.andExpect(jsonPath("$").isNotEmpty());
		resultActions.andExpect(jsonPath("$.[0].id", is(Integer.valueOf(savedCharacter.getId().toString()))));
		resultActions.andExpect(jsonPath("$.[0].role", is(savedCharacter.getRole())));
		resultActions.andExpect(jsonPath("$.[0].name", is(savedCharacter.getName())));
		resultActions.andExpect(jsonPath("$.[0].school", is(savedCharacter.getSchool())));
		resultActions.andExpect(jsonPath("$.[0].house", is(savedCharacter.getHouse())));
		resultActions.andExpect(jsonPath("$.[0].patronus", is(savedCharacter.getPatronus())));
	}
	
	@Test
	public void getByID_returnACharacters_whenSucessful() throws Exception {
		Character savedCharacter = CharacterCreator.createCharacterDomain();
		
		ResultActions resultActions = this.mockMvc.perform(get("/api/characters/1"));

		resultActions.andExpect(status().isOk());
		resultActions.andExpect(jsonPath("$").isNotEmpty());
		resultActions.andExpect(jsonPath("$.id", is(Integer.valueOf(savedCharacter.getId().toString()))));
		resultActions.andExpect(jsonPath("$.role", is(savedCharacter.getRole())));
		resultActions.andExpect(jsonPath("$.name", is(savedCharacter.getName())));
		resultActions.andExpect(jsonPath("$.school", is(savedCharacter.getSchool())));
		resultActions.andExpect(jsonPath("$.house", is(savedCharacter.getHouse())));
		resultActions.andExpect(jsonPath("$.patronus", is(savedCharacter.getPatronus())));
	}
	
	@Test
	public void create_returnCharacterResponse_whenSucessful() throws Exception {
		Character savedCharacter = CharacterCreator.createCharacterDomain();
		CharacterRequestDTO characterRequest = CharacterCreator.createCharacterRequest();
		
		ResultActions resultActions = this.mockMvc.perform(post("/api/characters")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(characterRequest)));
		
		resultActions.andExpect(status().isCreated());
		resultActions.andExpect(jsonPath("$").isNotEmpty());
		resultActions.andExpect(jsonPath("$.id", is(Integer.valueOf(savedCharacter.getId().toString()))));
		resultActions.andExpect(jsonPath("$.role", is(savedCharacter.getRole())));
		resultActions.andExpect(jsonPath("$.name", is(savedCharacter.getName())));
		resultActions.andExpect(jsonPath("$.school", is(savedCharacter.getSchool())));
		resultActions.andExpect(jsonPath("$.house", is(savedCharacter.getHouse())));
		resultActions.andExpect(jsonPath("$.patronus", is(savedCharacter.getPatronus())));
	}
	
	@Test
	public void create_willThrowResourceNotFound_whenHouseIdIsInvalid() throws Exception {
		when(this.service.createCharacter(ArgumentMatchers.any(CharacterRequestDTO.class)))
			.thenThrow(new ResourceNotFoundException("Teste Resource Not Found"));
		
		CharacterRequestDTO characterRequest = CharacterCreator.createCharacterRequest();
		
		this.mockMvc.perform(post("/api/characters")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(characterRequest)))
				.andExpect(status().isNotFound())
				.andExpect(result -> assertThat(result.getResolvedException() 
						 instanceof ResourceNotFoundException));
	}
	
	@Test
	public void replace_updatesACharacter_whenSucessful() throws Exception {
		Character savedCharacter = CharacterCreator.createCharacterDomain();
		CharacterRequestDTO characterRequest = CharacterCreator.createCharacterRequest();
		
		ResultActions resultActions = this.mockMvc.perform(put("/api/characters/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(characterRequest)));
		
		resultActions.andExpect(status().isOk());
		resultActions.andExpect(jsonPath("$").isNotEmpty());
		resultActions.andExpect(jsonPath("$.id", is(Integer.valueOf(savedCharacter.getId().toString()))));
		resultActions.andExpect(jsonPath("$.role", is(savedCharacter.getRole())));
		resultActions.andExpect(jsonPath("$.name", is(savedCharacter.getName())));
		resultActions.andExpect(jsonPath("$.school", is(savedCharacter.getSchool())));
		resultActions.andExpect(jsonPath("$.house", is(savedCharacter.getHouse())));
		resultActions.andExpect(jsonPath("$.patronus", is(savedCharacter.getPatronus())));
	}
	
	@Test
	public void delete_removesCharacter_whenSucessful() throws Exception {
		ResultActions resultActions = this.mockMvc.perform(delete("/api/characters/1"));
		
		resultActions.andExpect(status().isNoContent());
	}
}
