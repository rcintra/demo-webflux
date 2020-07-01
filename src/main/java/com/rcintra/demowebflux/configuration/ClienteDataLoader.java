package com.rcintra.demowebflux.configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.rcintra.demowebflux.domain.Cliente;
import com.rcintra.demowebflux.repository.ClienteRepository;

import reactor.core.publisher.Flux;

@Component
public class ClienteDataLoader implements ApplicationRunner {

	private static final Logger log = LoggerFactory.getLogger(ClienteDataLoader.class);

	private final ClienteRepository clienteRepository;
	
	ClienteDataLoader(final ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
	
	@Override
	public void run(final ApplicationArguments args) {
		if (clienteRepository.count().block() == 0L) {
			Supplier<String> idSupplier = getIdSequenceSupplier();
			BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(getClass()
                            .getClassLoader()
                            .getResourceAsStream("clientes.txt"))
            );
			Flux.fromStream(
                    bufferedReader.lines()
                            .filter(l -> !l.trim().isEmpty())
                            .map(l -> new Cliente(idSupplier.get(), l))
				).subscribe(m -> log.info("Novos clientes: {}", m.getNome()));
			log.info("Repository contem agora {} entidades.",
					clienteRepository.count().block());
		}
	}
	
	private Supplier<String> getIdSequenceSupplier() {
        return new Supplier<String>() {
            Long l = 0L;

            @Override
            public String get() {
                // adds padding zeroes
                return String.format("%05d", l++);
            }
        };
    }
	
}
