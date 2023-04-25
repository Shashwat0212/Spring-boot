package com.shopme.admin.setting.country;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopme.common.entity.Country;

@SpringBootTest
@AutoConfigureMockMvc
public class CountryRestControllerTests {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;
	@Autowired
	CountryRepository repo;

	// @Test
	// @WithMockUser(username = "nam@codejava.net", password = "nam2020",
	// authorities = "Admin")
	// public void testListCountries() throws Exception {
	// String url = "/countries/list";
	// MvcResult result = mockMvc.perform(get(url)).andExpect(status().isOk())
	// .andDo(print()).andReturn();
	// String jsonResponse = result.getResponse().getContentAsString();
	// Country[] countries = objectmapper.readValue(jsonResponse,
	// Country[].class);
	// assertThat(countries).hasSizeGreaterThan(0);
	// }
	// @Test
	// @WithMockUser(username = "nam@codejava.net", password = "nam2020",
	// authorities = "Admin")
	// public void testCreateCountry() throws Exception {
	// String url = "/countries/save";
	// String countryName = "Germany";
	// String countryCode = "DE";
	// Country country = new Country(countryName, countryCode);
	// MvcResult result = mockMvc
	// .perform(post(url).contentType("application/json")
	// .content(objectMapper.writeValueAsString(country))
	// .with(csrf()))
	// .andDo(print()).andExpect(status().isOk()).andReturn();
	// String response = result.getResponse().getContentAsString();
	// Integer countryId = Integer.parseInt(response);
	// Optional<Country> findById = repo.findById(countryId);
	// assertThat(findById.isPresent());
	// Country savedCountry = findById.get();
	// assertThat(savedCountry.getName()).isEqualTo(countryName);
	// }
	// @Test
	// @WithMockUser(username = "nam@codejava.net", password = "something",
	// roles = "ADMIN")
	// public void testUpdateCountry() throws JsonProcessingException, Exception
	// {
	// String url = "/countries/save";
	//
	// Integer countryId = 7;
	// String countryName = "China";
	// String countryCode = "CN";
	// Country country = new Country(countryId, countryName, countryCode);
	//
	// mockMvc.perform(post(url).contentType("application/json")
	// .content(objectMapper.writeValueAsString(country)).with(csrf()))
	// .andDo(print()).andExpect(status().isOk())
	// .andExpect(content().string(String.valueOf(countryId)));
	//
	// Optional<Country> findById = repo.findById(countryId);
	// assertThat(findById.isPresent());
	//
	// Country savedCountry = findById.get();
	//
	// assertThat(savedCountry.getName()).isEqualTo(countryName);
	// }
	@Test
	@WithMockUser(username = "nam@codejava.net", password = "something", roles = "ADMIN")
	public void testDeleteCountry() throws Exception {

		Integer countryId = 7;
		String url = "/countries/delete/" + countryId;

		mockMvc.perform(get(url)).andExpect(status().isOk());

		Optional<Country> findById = repo.findById(countryId);
		assertThat(findById).isNotPresent();

	}
}
