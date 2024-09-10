package fr.eni.encheres.dal;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import fr.eni.encheres.bo.Categorie;
import lombok.extern.slf4j.Slf4j;

@DataJpaTest
@Slf4j
public class TestCategorie {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private CategorieRepository categorieRepository;

	@Test
	public void test01_categorie_save() {
		Categorie categorie = Categorie
				.builder()
				.libelle("Ameublement")
				.build();

		Categorie entite = categorieRepository.save(categorie);
		log.info(categorie.toString());
		assertThat(entite.getId()).isGreaterThan(0);
	}

	@Test
	public void test02_categorie_findById() {
		Categorie categorie = Categorie.builder()
				.libelle("Ameublement")
				.build();

		entityManager.persist(categorie);
		entityManager.flush();
		log.info(categorie.toString());
		assertThat(categorie.getId()).isGreaterThan(0);
		
		long entiteId = categorie.getId();
		Optional<Categorie> op = categorieRepository.findById(entiteId);
		assertThat(op.isPresent()).isTrue();
		Categorie categorieFetch = op.get();
		assertThat(categorieFetch).isNotNull();
		assertThat(categorieFetch.getId()).isEqualTo(entiteId);
		assertThat(categorieFetch.getLibelle()).isEqualTo(categorie.getLibelle());
		log.info(categorieFetch.toString());
	}
	
	@Test
	public void test03_categorie_Update() {
		Categorie categorie = Categorie.builder()
				.libelle("Ameublement")
				.build();

		entityManager.persist(categorie);
		entityManager.flush();
		log.info(categorie.toString());
		assertThat(categorie.getId()).isGreaterThan(0);
		log.info("Origine : " + categorie.toString());
		
		categorie.setLibelle("BUFFET");
		
		Categorie categorieSauver = categorieRepository.save(categorie);
		assertThat(categorieSauver.getId()).isEqualTo(categorie.getId());
		assertThat(categorieSauver.getLibelle()).isEqualTo(categorie.getLibelle());
		log.info("Altérée : " + categorieSauver.toString());
	}
	
	@Test
	public void test04_categorie_delete() {
		Categorie categorie = Categorie.builder()
				.libelle("Ameublement")
				.build();

		entityManager.persist(categorie);
		entityManager.flush();
		log.info(categorie.toString());
		assertThat(categorie.getId()).isGreaterThan(0);
		
		categorieRepository.delete(categorie);
		
		Categorie adresseDel = entityManager.getEntityManager().find(Categorie.class, categorie.getId());
		assertThat(adresseDel).isNull();
	}
	
	@Test
	public void test05_categorie_findAll_Empty() {
		List<Categorie> lstAdresse = categorieRepository.findAll();
		assertThat(lstAdresse).isEmpty();
	}
	
	@Test
	public void test05_categorie_findAll() {
		jeuDeDonnes();
		
		List<Categorie> adresses = categorieRepository.findAll();
		assertThat(adresses.size()).isGreaterThan(0);
		adresses.forEach(a -> {
			log.info(a.toString());
			});
	}
	
	public void jeuDeDonnes() {
		List<Categorie> lstCategorie = new ArrayList<>();
		lstCategorie.add(Categorie
				.builder()
				.libelle("Ameublement")
				.build()
				);
		lstCategorie.add(Categorie
				.builder()
				.libelle("Informatique")
				.build()
				);
		lstCategorie.add(Categorie
				.builder()
				.libelle("Sport & Loisirs")
				.build()
				);
		
		lstCategorie.forEach(e -> {
			entityManager.persist(e);
		});
		entityManager.flush();
	}
}
