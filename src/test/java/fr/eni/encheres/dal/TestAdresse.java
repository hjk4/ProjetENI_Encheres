package fr.eni.encheres.dal;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import fr.eni.encheres.bo.Adresse;
import lombok.extern.slf4j.Slf4j;

@DataJpaTest
@Slf4j
public class TestAdresse {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private AdresseRepository adresseRepository;

	@Test
	public void test01_adresse_save() {
		Adresse adresse = Adresse.builder()
				.rue("2B RUE BENJAMIN FRANKLIN")
				.codePostal("44800")
				.ville("SAINT HERBLAIN")
				.adresseEni(false)
				.build();

		Adresse entite = adresseRepository.save(adresse);
		log.info(adresse.toString());
		assertThat(entite.getId()).isGreaterThan(0);
	}

	@Test
	public void test02_adresse_findById() {
		Adresse adresse = Adresse.builder()
				.rue("2B RUE BENJAMIN FRANKLIN")
				.codePostal("44800")
				.ville("SAINT HERBLAIN")
				.adresseEni(false)
				.build();

		entityManager.persist(adresse);
		entityManager.flush();
		log.info(adresse.toString());
		assertThat(adresse.getId()).isGreaterThan(0);
		
		long entiteId = adresse.getId();
		Optional<Adresse> op = adresseRepository.findById(entiteId);
		assertThat(op.isPresent()).isTrue();
		Adresse adresseFetch = op.get();
		assertThat(adresseFetch).isNotNull();
		assertThat(adresseFetch.getId()).isEqualTo(entiteId);
		assertThat(adresseFetch.getRue()).isEqualTo(adresse.getRue());
		assertThat(adresseFetch.getCodePostal()).isEqualTo(adresse.getCodePostal());
		assertThat(adresseFetch.getVille()).isEqualTo(adresse.getVille());
		assertThat(adresseFetch.isAdresseEni()).isFalse();
		log.info(adresseFetch.toString());
	}
	
	@Test
	public void test03_adresse_Update() {
		Adresse adresse = Adresse.builder()
				.rue("2B RUE BENJAMIN FRANKLIN")
				.codePostal("44800")
				.ville("SAINT HERBLAIN")
				.adresseEni(false)
				.build();

		entityManager.persist(adresse);
		entityManager.flush();
		log.info(adresse.toString());
		assertThat(adresse.getId()).isGreaterThan(0);
		log.info("Origine : " + adresse.toString());
		
		adresse.setVille("SAINT CLOCLO");
		
		Adresse adresseSauver = adresseRepository.save(adresse);
		assertThat(adresseSauver.getId()).isEqualTo(adresse.getId());
		assertThat(adresseSauver.getVille()).isEqualTo(adresse.getVille());
		log.info("Altérée : " + adresseSauver.toString());
	}
	
	@Test
	public void test04_adresse_delete() {
		Adresse adresse = Adresse.builder()
				.rue("2B RUE BENJAMIN FRANKLIN")
				.codePostal("44800")
				.ville("SAINT HERBLAIN")
				.adresseEni(false)
				.build();

		entityManager.persist(adresse);
		entityManager.flush();
		log.info(adresse.toString());
		assertThat(adresse.getId()).isGreaterThan(0);
		
		adresseRepository.delete(adresse);
		
		Adresse adresseDel = entityManager.getEntityManager().find(Adresse.class, adresse.getId());
		assertThat(adresseDel).isNull();
	}
	
	@Test
	public void test05_adresse_findAll_Empty() {
		List<Adresse> lstAdresse = adresseRepository.findAll();
		assertThat(lstAdresse).isEmpty();
	}
	
	@Test
	public void test05_adresse_findAll() {
		jeuDeDonnes();
		
		List<Adresse> adresses = adresseRepository.findAll();
		assertThat(adresses.size()).isGreaterThan(0);
		adresses.forEach(a -> {
			log.info(a.toString());
			});
	}
	
	public void jeuDeDonnes() {
		List<Adresse> lstAdresse = new ArrayList<>();
		lstAdresse.add(Adresse
						.builder()
						.rue("2B RUE BENJAMIN FRANKLIN")
						.codePostal("44800")
						.ville("SAINT HERBLAIN")
						.adresseEni(true)
						.build()
						);
		lstAdresse.add(Adresse
				.builder()
				.rue("3 RUE MICKAËL FARADAY")
				.codePostal("44800")
				.ville("SAINT HERBLAIN")
				.adresseEni(true)
				.build()
				);
		lstAdresse.add(Adresse
				.builder()
				.rue("8 RUE LÉO LAGRANGE")
				.codePostal("35131")
				.ville("CHARTRES DE BRETAGNE")
				.adresseEni(true)
				.build()
				);
		
		lstAdresse.forEach(e -> {
			entityManager.persist(e);
		});
		entityManager.flush();
	}
}
