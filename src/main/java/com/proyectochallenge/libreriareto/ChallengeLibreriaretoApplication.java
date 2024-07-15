package com.proyectochallenge.libreriareto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.proyectochallenge.libreriareto.principal.IntefazPrincipal;
import com.proyectochallenge.libreriareto.repository.LibreriaRepository;
import com.proyectochallenge.libreriareto.repository.AutoresRepository;

@SpringBootApplication
public class ChallengeLibreriaretoApplication implements CommandLineRunner {

	@Autowired
	private LibreriaRepository libreriaRepositorio;
	@Autowired
	private AutoresRepository autoresRepositorio;

    public static void main(String[] args) {
		SpringApplication.run(ChallengeLibreriaretoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		IntefazPrincipal libreria = new IntefazPrincipal(libreriaRepositorio, autoresRepositorio);
		libreria.menuDeAplicacion();
	}
}
