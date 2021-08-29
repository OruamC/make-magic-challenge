package com.dextra.challenge.makemagic.controller.it;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.dextra.challenge.makemagic.controller.CharacterController;
import com.dextra.challenge.makemagic.domains.dto.CharacterRequestDTO;
import com.dextra.challenge.makemagic.service.CharacterService;
import com.dextra.challenge.makemagic.util.CharacterCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CharacterController.class)
public class CharacterControllerItTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CharacterService service;

	@Test
	void create_willThrowMethodArgumentNotValidException_whenSomeFieldIsInvalid() throws Exception {
		CharacterRequestDTO characterRequest = CharacterCreator.createCharacterRequest();
		characterRequest.setName("");

		this.mockMvc.perform(post("/api/characters")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(characterRequest)))
				.andExpect(status().isUnprocessableEntity());
	}
	
	@Test
	public void getAll_throwConstraintViolationException_whenHouseParamNotSucess() throws Exception {
		this.mockMvc.perform(get("/api/characters")
				.queryParam("house", "test")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isUnprocessableEntity())
				.andExpect(result -> assertThat(result.getResolvedException() 
						 instanceof ConstraintViolationException));
	}
}
