package com.test.secondlesson;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class SecondlessonApplicationTests {
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	public void getAllDogBreeds() {
		ResponseEntity<List> response = restTemplate.withBasicAuth("user","password").getForEntity("http://localhost:" + port + "/retrieveBreeds", List.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void getAllDogNames() {
		ResponseEntity<List> response = restTemplate.withBasicAuth("user","password").getForEntity("http://localhost:" + port + "/retrieveNames", List.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void getDogBreedById() {
		restTemplate.withBasicAuth("user", "password");
		ResponseEntity<String> response = restTemplate.withBasicAuth("user","password").getForEntity("http://localhost:" + port + "/retrieveById/1", String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
}
