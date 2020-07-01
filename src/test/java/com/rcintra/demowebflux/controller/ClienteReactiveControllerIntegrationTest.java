package com.rcintra.demowebflux.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;

import com.rcintra.demowebflux.configuration.ClienteDataLoader;
import com.rcintra.demowebflux.domain.Cliente;
import com.rcintra.demowebflux.repository.ClienteRepository;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.BDDMockito.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ClienteReactiveControllerIntegrationTest {

	@MockBean
	private ClienteRepository clienteRepository;
	
	@MockBean
    private ClienteDataLoader clienteDataLoader;

	@LocalServerPort
	private int serverPort;

	private WebClient webClient;

	private Flux<Cliente> clienteFlux;

	@Before
	public void setUp() {
		this.webClient = WebClient.create("http://localhost:" + serverPort);
		clienteFlux = Flux.just(
				new Cliente("00001", "Cliente 1"), 
				new Cliente("00002", "Cliente 2"),
				new Cliente("00003", "Cliente 3"),
				new Cliente("00004", "Cliente 4")
				);
	}

	@Test
	public void simpleGetRequest() {
		// given
		given(clienteRepository.findAll()).willReturn(clienteFlux);

		// when
		Flux<Cliente> receivedFlux = webClient.get().uri("/clientes-reactive").accept(MediaType.TEXT_EVENT_STREAM)
				.exchange().flatMapMany(response -> response.bodyToFlux(Cliente.class));

		// then
		StepVerifier.create(receivedFlux)
				.expectNext(new Cliente("00001", "Cliente 1"))
				.expectNext(new Cliente("00002", "Cliente 2"))
				.expectNext(new Cliente("00003", "Cliente 3"))
				.expectNext(new Cliente("00004", "Cliente 4"))
				.expectComplete()
				.verify();

	}
}
