package com.rcintra.demowebflux.controller;

import java.time.Duration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rcintra.demowebflux.domain.Cliente;
import com.rcintra.demowebflux.repository.ClienteRepository;

import reactor.core.publisher.Flux;

@RestController
public class ClienteReactiveController {
	
	private static final int DELAY_PER_ITEM_MS = 100;
	private final ClienteRepository clienteRepository;

	public ClienteReactiveController(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	@GetMapping("/clientes-reactive")
	public Flux<Cliente> getClienteFlux() {
		return clienteRepository.findAll().delayElements(Duration.ofMillis(DELAY_PER_ITEM_MS));
	}

}
