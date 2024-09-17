package fr.eni.encheres;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.encheres.bo.Adresse;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.AdresseRepository;
import fr.eni.encheres.dal.UtilisateurRepository;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class CreateUsers {
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
	private AdresseRepository adresseRepository;
	
	/*
	@BeforeEach
	public void clearTables() {
		utilisateurRepository.deleteAll();
		adresseRepository.deleteAll();
	}
	*/
	
	@Test
	public void test01_Populate_Users() {
		Adresse adresseNantes1 = Adresse
								.builder()
								.rue("2B RUE BENJAMIN FRANKLIN")
								.codePostal("44800")
								.ville("SAINT HERBLAIN")
								.adresseEni(true)
								.build();
		
		Adresse adresseDB = adresseRepository.save(adresseNantes1);
		assertThat(adresseDB.getId()).isGreaterThan(0);
		
		
		Adresse adresseNantes2 = Adresse
				.builder()
				.rue("3 RUE MICKAËL FARADAY")
				.codePostal("44800")
				.ville("SAINT HERBLAIN")
				.adresseEni(true)
				.build();
		
		adresseDB = adresseRepository.save(adresseNantes2);
		assertThat(adresseDB.getId()).isGreaterThan(0);
		
		Adresse adresseRennes = Adresse
				.builder()
				.rue("8 RUE LÉO LAGRANGE")
				.codePostal("35131")
				.ville("CHARTRES DE BRETAGNE")
				.adresseEni(true)
				.build();
		
		adresseDB = adresseRepository.save(adresseRennes);
		assertThat(adresseDB.getId()).isGreaterThan(0);
		
		Adresse adresseQuimper = Adresse
				.builder()
				.rue("2 RUE GEORGES PERROS")
				.codePostal("29000")
				.ville("QUIMPER")
				.adresseEni(true)
				.build();
		
		adresseDB = adresseRepository.save(adresseQuimper);
		assertThat(adresseDB.getId()).isGreaterThan(0);
		
		Adresse adresseNiort = Adresse
				.builder()
				.rue("19 AVENUE LÉO LAGRANGE")
				.codePostal("79000")
				.ville("NIORT")
				.adresseEni(true)
				.build();
		
		adresseDB = adresseRepository.save(adresseNiort);
		assertThat(adresseDB.getId()).isGreaterThan(0);
		
		Utilisateur utilisateurCoachAdmin = Utilisateur
												.builder()
												.pseudo("coach_admin")
												.nom("COACH")
												.prenom("Eni")
												.email("coach@campus-eni.fr")
												.motDePasse("{bcrypt}$2a$10$tKtwPcqKLa6Hw8nnGr96LeN.hxzhjXGzQe1DuN6hjlnRGJgiTqcMu")
												.admin(true)
												.adresse(adresseNantes1)
												.build();
		
		Utilisateur utilisateurDB = utilisateurRepository.save(utilisateurCoachAdmin);
		assertThat(utilisateurDB.getPseudo()).isEqualTo(utilisateurCoachAdmin.getPseudo());
		
		Utilisateur utilisateurToto = Utilisateur
				.builder()
				.pseudo("coach_toto")
				.nom("Toto")
				.prenom("Eni")
				.email("toto@campus-eni.fr")
				.motDePasse("{bcrypt}$2a$10$tKtwPcqKLa6Hw8nnGr96LeN.hxzhjXGzQe1DuN6hjlnRGJgiTqcMu")
				.admin(false)
				.adresse(adresseNantes2)
				.build();
		
		utilisateurDB = utilisateurRepository.save(utilisateurToto);
		assertThat(utilisateurDB.getPseudo()).isEqualTo(utilisateurToto.getPseudo());
		
		Utilisateur utilisateurTiti = Utilisateur
				.builder()
				.pseudo("coach_titi")
				.nom("Titi")
				.prenom("Eni")
				.email("titi@campus-eni.fr")
				.motDePasse("{bcrypt}$2a$10$tKtwPcqKLa6Hw8nnGr96LeN.hxzhjXGzQe1DuN6hjlnRGJgiTqcMu")
				.admin(false)
				.adresse(adresseRennes)
				.build();
		
		utilisateurDB = utilisateurRepository.save(utilisateurTiti);
		assertThat(utilisateurDB.getPseudo()).isEqualTo(utilisateurTiti.getPseudo());
		
		Utilisateur utilisateurTata = Utilisateur
				.builder()
				.pseudo("coach_tata")
				.nom("Tata")
				.prenom("Eni")
				.email("tata@campus-eni.fr")
				.motDePasse("{bcrypt}$2a$10$tKtwPcqKLa6Hw8nnGr96LeN.hxzhjXGzQe1DuN6hjlnRGJgiTqcMu")
				.admin(false)
				.adresse(adresseNantes2)
				.build();
		
		utilisateurDB = utilisateurRepository.save(utilisateurTata);
		assertThat(utilisateurDB.getPseudo()).isEqualTo(utilisateurTata.getPseudo());
	}
}
