package com.rcintra.demowebflux.repository;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;

import com.rcintra.demowebflux.domain.Cliente;

public interface ClienteRepository extends ReactiveSortingRepository<Cliente, String> {

}
