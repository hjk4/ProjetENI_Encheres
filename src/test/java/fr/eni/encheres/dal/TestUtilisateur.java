package fr.eni.encheres.dal;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.MethodName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import fr.eni.encheres.bo.Adresse;
import fr.eni.encheres.bo.Utilisateur;
import lombok.extern.slf4j.Slf4j;



@DataJpaTest
@Slf4j
@TestMethodOrder(MethodName.class) 
public class TestUtilisateur {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
	private AdresseRepository adresseRepository;
	// TODO Modify the tests to avoid using cascades
	@Test
	public void test01_utilisateur_save() {
		Utilisateur utilisateur = Utilisateur
				.builder()
				.pseudo("coach_admin")
				.nom("COACH")
				.prenom("ENI")
				.email("coach@campus-eni.fr")
				.motDePasse("{bcrypt}$2a$10$tKtwPcqKLa6Hw8nnGr96LeN.hxzhjXGzQe1DuN6hjlnRGJgiTqcMu")
				.admin(true)
				.build();
		Adresse adresse = Adresse.builder()
				.rue("testSave")
				.codePostal("44800")
				.ville("SAINT HERBLAIN")
				.adresseEni(false)
				.build();
		
		utilisateur.setAdresse(adresse);
		
		Adresse adresseDB = adresseRepository.save(adresse);
		Utilisateur entite = utilisateurRepository.save(utilisateur);
		log.info(utilisateur.toString());
		assertThat(entite.getPseudo()).isEqualTo(utilisateur.getPseudo());
		assertThat(entite.getAdresse()).isEqualTo(adresseDB);
	}

	@Test
	public void test02_utilisateur_findById() {
		Utilisateur utilisateur = Utilisateur
				.builder()
				.pseudo("coach_admin")
				.nom("COACH")
				.prenom("ENI")
				.email("coach@campus-eni.fr")
				.motDePasse("{bcrypt}$2a$10$tKtwPcqKLa6Hw8nnGr96LeN.hxzhjXGzQe1DuN6hjlnRGJgiTqcMu")
				.admin(true)
				.build();
		Adresse adresse = Adresse.builder()
				.rue("2B RUE BENJAMIN FRANKLIN")
				.codePostal("44800")
				.ville("SAINT HERBLAIN")
				.adresseEni(false)
				.build();
		
		utilisateur.setAdresse(adresse);

		entityManager.persist(utilisateur);
		entityManager.flush();
		log.info(utilisateur.toString());
		
		String entiteId = utilisateur.getPseudo();
		Optional<Utilisateur> op = utilisateurRepository.findById(entiteId);
		assertThat(op.isPresent()).isTrue();
		Utilisateur utilisateurFetch = op.get();
		assertThat(utilisateurFetch).isNotNull();
		assertThat(utilisateurFetch.getPseudo()).isEqualTo(entiteId);
		assertThat(utilisateurFetch.getNom()).isEqualTo(utilisateur.getNom());
		assertThat(utilisateurFetch.getPrenom()).isEqualTo(utilisateur.getPrenom());
		assertThat(utilisateurFetch.getEmail()).isEqualTo(utilisateur.getEmail());
		assertThat(utilisateurFetch.getMotDePasse()).isEqualTo(utilisateur.getMotDePasse());
		assertThat(utilisateurFetch.isAdmin()).isEqualTo(utilisateur.isAdmin());
		assertThat(utilisateurFetch.getAdresse()).isEqualTo(utilisateur.getAdresse());
		log.info(utilisateurFetch.toString());
	}
	
	@Test
	public void test03_utilisateur_Update() {
		Utilisateur utilisateur = Utilisateur
				.builder()
				.pseudo("coach_admin")
				.nom("COACH")
				.prenom("ENI")
				.email("coach@campus-eni.fr")
				.motDePasse("{bcrypt}$2a$10$tKtwPcqKLa6Hw8nnGr96LeN.hxzhjXGzQe1DuN6hjlnRGJgiTqcMu")
				.admin(true)
				.build();
		Adresse adresse = Adresse.builder()
				.rue("2B RUE BENJAMIN FRANKLIN")
				.codePostal("44800")
				.ville("SAINT HERBLAIN")
				.adresseEni(false)
				.build();
		
		utilisateur.setAdresse(adresse);

		entityManager.persist(utilisateur);
		entityManager.flush();
		log.info(utilisateur.toString());
		log.info("Origine : " + utilisateur.toString());
		
		utilisateur.setNom("CoachBigBoss");
		utilisateur.getAdresse().setVille("Ville du Big Coach");
		
		Utilisateur utilisateurSauver = utilisateurRepository.save(utilisateur);
		assertThat(utilisateurSauver.getPseudo()).isEqualTo(utilisateur.getPseudo());
		assertThat(utilisateurSauver.getNom()).isEqualTo(utilisateur.getNom());
		assertThat(utilisateurSauver.getAdresse().getVille()).isEqualTo(utilisateur.getAdresse().getVille());
		log.info("Altérée : " + utilisateurSauver.toString());
	}
	
	@Test
	public void test04_utilisateur_delete() {
		Utilisateur utilisateur = Utilisateur
				.builder()
				.pseudo("coach_admin")
				.nom("COACH")
				.prenom("ENI")
				.email("coach@campus-eni.fr")
				.motDePasse("{bcrypt}$2a$10$tKtwPcqKLa6Hw8nnGr96LeN.hxzhjXGzQe1DuN6hjlnRGJgiTqcMu")
				.admin(true)
				.build();
		Adresse adresse = Adresse.builder()
				.rue("2B RUE BENJAMIN FRANKLIN")
				.codePostal("44800")
				.ville("SAINT HERBLAIN")
				.adresseEni(false)
				.build();
		
		utilisateur.setAdresse(adresse);

		entityManager.persist(utilisateur);
		entityManager.flush();
		log.info(utilisateur.toString());
		
		utilisateurRepository.delete(utilisateur);
		
		Utilisateur utilisateurDel = entityManager.getEntityManager().find(Utilisateur.class, utilisateur.getPseudo());
		assertThat(utilisateurDel).isNull();
		
		Adresse adresseNotDel = entityManager.getEntityManager().find(Adresse.class, adresse.getId());
		assertThat(adresseNotDel).isNotNull();
		assertThat(adresseNotDel.getId()).isGreaterThan(0);
		log.info(adresseNotDel.toString());
	}
	
	@Test
	public void test05_utilisateur_findAll_Empty() {
		List<Utilisateur> lstUtilisateur = utilisateurRepository.findAll();
		assertThat(lstUtilisateur).isEmpty();
	}
	
	@Test
	public void test05_adresse_findAll() {
		jeuDeDonnes();
		
		List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
		utilisateurs.forEach(u -> {
			log.info(u.toString());
			});
		assertThat(utilisateurs.size()).isGreaterThan(0);
	}
	
	public void jeuDeDonnes() {
		List<Utilisateur> lstUtilisateur = new ArrayList<>();
		lstUtilisateur.add(Utilisateur
				.builder()
				.pseudo("coach_admin")
				.nom("COACH")
				.prenom("ENI")
				.email("coach@campus-eni.fr")
				.motDePasse("{bcrypt}$2a$10$tKtwPcqKLa6Hw8nnGr96LeN.hxzhjXGzQe1DuN6hjlnRGJgiTqcMu")
				.admin(true)
				.adresse(Adresse
						.builder()
						.rue("2B RUE BENJAMIN FRANKLIN")
						.codePostal("44800")
						.ville("SAINT HERBLAIN")
						.adresseEni(false)
						.build())
				.build()
				);
		lstUtilisateur.add(Utilisateur
				.builder()
				.pseudo("coach_toto")
				.nom("Toto")
				.prenom("Eni")
				.email("toto@campus-eni.fr")
				.motDePasse("{bcrypt}$2a$10$tKtwPcqKLa6Hw8nnGr96LeN.hxzhjXGzQe1DuN6hjlnRGJgiTqcMu")
				.adresse(Adresse
						.builder()
						.rue("2B RUE BENJAMIN FRANKLIN")
						.codePostal("44800")
						.ville("SAINT HERBLAIN")
						.adresseEni(false)
						.build())
				.build()
				);
		lstUtilisateur.add(Utilisateur
				.builder()
				.pseudo("coach_titi")
				.nom("Titi")
				.prenom("ENI")
				.email("titi@campus-eni.fr")
				.motDePasse("{bcrypt}$2a$10$tKtwPcqKLa6Hw8nnGr96LeN.hxzhjXGzQe1DuN6hjlnRGJgiTqcMu")
				.admin(false)
				.adresse(Adresse
						.builder()
						.rue("2B RUE BENJAMIN FRANKLIN")
						.codePostal("44800")
						.ville("SAINT HERBLAIN")
						.adresseEni(false)
						.build())
				.build()
				);
		
		lstUtilisateur.forEach(e -> {
			entityManager.persist(e);
		});
		entityManager.flush();
	}
}
