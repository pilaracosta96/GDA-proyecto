package com.adquisicion_g7.adquisicion;

import com.adquisicion_g7.adquisicion.entities.Bibliografia;
import com.adquisicion_g7.adquisicion.entities.Editorial;
import com.adquisicion_g7.adquisicion.entities.TipoMaterial;
import com.adquisicion_g7.adquisicion.repository.BibliografiaRepository;
import com.adquisicion_g7.adquisicion.repository.EditorialRepository;
import com.adquisicion_g7.adquisicion.repository.TipoMaterialRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Set;

@SpringBootApplication
public class AdquisicionApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdquisicionApplication.class, args);
	}

	/*@Bean
	CommandLineRunner commandLineRunner(
			BibliografiaRepository bibliografiaRepository,
			TipoMaterialRepository tipoMaterialRepository,
			EditorialRepository editorialRepository
	){
		return args -> {
			TipoMaterial tipoMaterial1 = tipoMaterialRepository.save(new TipoMaterial( null, "Libro", null));
			TipoMaterial tipoMaterial2 = tipoMaterialRepository.save(new TipoMaterial( null, "Revista", null));
			TipoMaterial tipoMaterial3 = tipoMaterialRepository.save(new TipoMaterial( null, "Apunte", null));
			Editorial editorial1= editorialRepository.save(new Editorial( null, "Atlantida",null));
			Editorial editorial2= editorialRepository.save(new Editorial( null, "Eudeba", null));
			Editorial editorial3= editorialRepository.save(new Editorial( null, "Utn", null));
			Bibliografia bibliografia1 = bibliografiaRepository.save(new Bibliografia(null, "Rayuela", "Julio", "Cortazar", 1963, editorial1, 1234567891011L, 12L, 4522F, tipoMaterial1));
			Bibliografia bibliografia2 = bibliografiaRepository.save(new Bibliografia(null, "El Aleph", "Jorge Luis", "Borges", 1948, editorial2, 1234567891012L, 123L, 4412F, tipoMaterial1));
			Bibliografia bibliografia3 = bibliografiaRepository.save(new Bibliografia(null, "Análisis Numérico", "Diego", "Amiconi", 2020, editorial3, 1234567891019L, 124L, 1200F, tipoMaterial3));


			editorial1.setBibliografias(Set.of(bibliografia1,bibliografia2));
			editorial3.setBibliografias(Set.of(bibliografia3));
			tipoMaterial1.setBibliografias(Set.of(bibliografia1,bibliografia2));
			TipoMaterial libro = tipoMaterialRepository.save(tipoMaterial1);
			Bibliografia bibliografia4 = bibliografiaRepository.findByIsbn(1234567891011L);
			System.out.println(bibliografia2.toString());

		};
	}*/
}
