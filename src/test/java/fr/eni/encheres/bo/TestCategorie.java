package fr.eni.encheres.bo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class TestCategorie {

	@Test
	void test01_CategorieNull() {
		Categorie categorie = Categorie
										.builder()
										.build();
		assertThat(categorie.getId()).isEqualTo(0);
		assertNull(categorie.getLibelle());
	}
	
	@Test
	void test02_Categorie() {
		Categorie categorie = Categorie
										.builder()
										.libelle("Informatique")
										.build();
		assertNotNull(categorie);
		assertThat(categorie.getLibelle()).isEqualTo(categorie.getLibelle());
		log.info(categorie.toString());
	}
}
