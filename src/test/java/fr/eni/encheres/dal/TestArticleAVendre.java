package fr.eni.encheres.dal;

import org.junit.jupiter.api.MethodOrderer.MethodName;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import fr.eni.encheres.bo.Adresse;
import fr.eni.encheres.bo.ArticleAVendre;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Utilisateur;
import lombok.extern.slf4j.Slf4j;

@DataJpaTest
@Slf4j
@TestMethodOrder(MethodName.class)
public class TestArticleAVendre {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired 
	private ArticleAVendreRepository articleAVendreRepository;
	// TODO Modify the tests to avoid using cascades
	@Test
	public void test01_ArticleAVendre_save() {
		
		Adresse adresse = Adresse
				.builder()
				.ville("Ville")
				.codePostal("78154")
				.rue("10 Rue des églantiers")
				.adresseEni(false)
				.build();
		
		Utilisateur utilisateur = Utilisateur
				.builder()
				.pseudo("Coach-admin")
				.nom("Coach")
				.prenom("ENI")
				.email("coachadmin@campus-eni.fr")
				.motDePasse("000")
				.admin(true)
				.adresse(adresse)
				.build();
		
		Categorie categorie = Categorie 
				.builder()
				.libelle("Informatique")
				.build();
		
		ArticleAVendre articleAVendre = ArticleAVendre
				.builder()
				.nom("Ordinateur Portable")
				.description("Ordinateur portable en bon état de marche")
				.photo(1)
				.dateDebutEncheres(Date.valueOf("2024-09-08"))
				.dateFinEncheres(Date.valueOf("2024-10-10"))
				.prixInitial(10)
				.prixVente(null)
				.adresse(adresse)
				.categorie(categorie)
				.vendeur(utilisateur)
				.build();
		
		ArticleAVendre articleAVendreDB = articleAVendreRepository.save(articleAVendre);
		assertThat(articleAVendreDB).isNotNull();
		log.info(articleAVendreDB.toString());
		assertThat(articleAVendreDB.getId()).isGreaterThan(0);
	}
	
	@Test
	public void test02_ArticleAVendre_findById() {
		Adresse adresse = Adresse
				.builder()
				.ville("Ville")
				.codePostal("78154")
				.rue("10 Rue des églantiers")
				.adresseEni(false)
				.build();
		
		Utilisateur utilisateur = Utilisateur
				.builder()
				.pseudo("Coach-admin")
				.nom("Coach")
				.prenom("ENI")
				.email("coachadmin@campus-eni.fr")
				.motDePasse("000")
				.admin(true)
				.adresse(adresse)
				.build();
		
		Categorie categorie = Categorie 
				.builder()
				.libelle("Informatique")
				.build();
		
		ArticleAVendre articleAVendre = ArticleAVendre
				.builder()
				.nom("Ordinateur Portable")
				.description("Ordinateur portable en bon état de marche")
				.photo(1)
				.dateDebutEncheres(Date.valueOf("2024-09-08"))
				.dateFinEncheres(Date.valueOf("2024-10-10"))
				.prixInitial(10)
				.prixVente(null)
				.adresse(adresse)
				.categorie(categorie)
				.vendeur(utilisateur)
				.build();
		
		entityManager.persist(articleAVendre);
		entityManager.flush();
		log.info("saved : " + articleAVendre.toString());
		
		Optional<ArticleAVendre> op = articleAVendreRepository.findById(articleAVendre.getId());
		assertThat(op.isPresent()).isTrue();
		ArticleAVendre articleAVendreFetch = op.get();
		assertThat(articleAVendreFetch.getId()).isEqualTo(articleAVendre.getId());
		assertThat(articleAVendreFetch.getNom()).isEqualTo(articleAVendre.getNom());
		assertThat(articleAVendreFetch.getDescription()).isEqualTo(articleAVendre.getDescription());
		assertThat(articleAVendreFetch.getPhoto()).isEqualTo(articleAVendre.getPhoto());
		assertThat(articleAVendreFetch.getDateDebutEncheres()).isEqualTo(articleAVendre.getDateDebutEncheres());
		assertThat(articleAVendreFetch.getDateFinEncheres()).isEqualTo(articleAVendre.getDateFinEncheres());
		assertThat(articleAVendreFetch.getPrixInitial()).isEqualTo(articleAVendre.getPrixInitial());
		assertThat(articleAVendreFetch.getPrixVente()).isEqualTo(articleAVendre.getPrixVente());
		assertThat(articleAVendreFetch.getAdresse()).isEqualTo(articleAVendre.getAdresse());
		assertThat(articleAVendreFetch.getVendeur()).isEqualTo(articleAVendre.getVendeur());
		assertThat(articleAVendreFetch.getCategorie()).isEqualTo(articleAVendre.getCategorie());
		log.info("fetched : " + articleAVendreFetch.toString());
	}
	
	@Test
	public void test03_ArticleAVendre_update() {
		Adresse adresse = Adresse
				.builder()
				.ville("Ville")
				.codePostal("78154")
				.rue("10 Rue des églantiers")
				.adresseEni(false)
				.build();
		
		Utilisateur utilisateur = Utilisateur
				.builder()
				.pseudo("Coach-admin")
				.nom("Coach")
				.prenom("ENI")
				.email("coachadmin@campus-eni.fr")
				.motDePasse("000")
				.admin(true)
				.adresse(adresse)
				.build();
		
		Categorie categorie = Categorie 
				.builder()
				.libelle("Informatique")
				.build();
		
		ArticleAVendre articleAVendre = ArticleAVendre
				.builder()
				.nom("Ordinateur Portable")
				.description("Ordinateur portable en bon état de marche")
				.photo(1)
				.dateDebutEncheres(Date.valueOf("2024-09-08"))
				.dateFinEncheres(Date.valueOf("2024-10-10"))
				.prixInitial(10)
				.prixVente(null)
				.adresse(adresse)
				.categorie(categorie)
				.vendeur(utilisateur)
				.build();
		
		entityManager.persist(articleAVendre);
		entityManager.flush();
		log.info("saved : " + articleAVendre.toString());
		
		articleAVendre.setNom("Ordinateur Fixe");
		articleAVendre.setDescription("Ordinateur fixe en bon état de marche");
		articleAVendre.setPrixVente(50);
		
		ArticleAVendre articleAVendreUpdate = articleAVendreRepository.save(articleAVendre);
		assertThat(articleAVendreUpdate.getId()).isEqualTo(articleAVendre.getId());
		assertThat(articleAVendreUpdate.getNom()).isEqualTo(articleAVendre.getNom());
		assertThat(articleAVendreUpdate.getDescription()).isEqualTo(articleAVendre.getDescription());
		assertThat(articleAVendreUpdate.getPrixVente()).isEqualTo(articleAVendre.getPrixVente());
		log.info("Updated :" + articleAVendreUpdate.toString());
	}
	
	@Test
	public void test04_ArticleAVendre_Delete() {
		Adresse adresse = Adresse
				.builder()
				.ville("Ville")
				.codePostal("78154")
				.rue("10 Rue des églantiers")
				.adresseEni(false)
				.build();
		
		Utilisateur utilisateur = Utilisateur
				.builder()
				.pseudo("Coach-admin")
				.nom("Coach")
				.prenom("ENI")
				.email("coachadmin@campus-eni.fr")
				.motDePasse("000")
				.admin(true)
				.adresse(adresse)
				.build();
		
		Categorie categorie = Categorie 
				.builder()
				.libelle("Informatique")
				.build();
		
		ArticleAVendre articleAVendre = ArticleAVendre
				.builder()
				.nom("Ordinateur Portable")
				.description("Ordinateur portable en bon état de marche")
				.photo(1)
				.dateDebutEncheres(Date.valueOf("2024-09-08"))
				.dateFinEncheres(Date.valueOf("2024-10-10"))
				.prixInitial(10)
				.prixVente(null)
				.adresse(adresse)
				.categorie(categorie)
				.vendeur(utilisateur)
				.build();
		
		entityManager.persist(articleAVendre);
		entityManager.flush();
		
		articleAVendreRepository.delete(articleAVendre);
		
		ArticleAVendre articleAVendreDel = entityManager.find(ArticleAVendre.class, articleAVendre.getId());
		assertThat(articleAVendreDel).isNull();
	}
	
	@Test
	public void test05_ArticleAVendre_FindAllEmpty() {
		List<ArticleAVendre> lstArticleAVendre = articleAVendreRepository.findAll();
		assertThat(lstArticleAVendre).isEmpty();
	}
	
	@Test
	public void test06_ArticleAVendre_FindAll() {
		jeuDeDonnees();
		
		List<ArticleAVendre> lstArticles = articleAVendreRepository.findAll();
		assertThat(lstArticles.size()).isGreaterThan(1);
		
		lstArticles.forEach( a -> {
			log.info(a.toString());
		});
	}
	
	private void jeuDeDonnees() {
		
		Adresse adresse = Adresse
				.builder()
				.ville("Ville1")
				.codePostal("78154")
				.rue("10 Rue des églantiers")
				.adresseEni(false)
				.build();
		
		Adresse adresse2 = Adresse
				.builder()
				.ville("Ville2")
				.codePostal("78154")
				.rue("10 Rue des églantiers")
				.adresseEni(false)
				.build();
		
		Adresse adresse3 = Adresse
				.builder()
				.ville("Ville3")
				.codePostal("78154")
				.rue("10 Rue des églantiers")
				.adresseEni(false)
				.build();
		
		Utilisateur utilisateur = Utilisateur
				.builder()
				.pseudo("Coach-admin")
				.nom("Coach")
				.prenom("ENI")
				.email("coachadmin@campus-eni.fr")
				.motDePasse("000")
				.admin(true)
				.adresse(adresse)
				.build();
		Utilisateur utilisateur2 = Utilisateur
				.builder()
				.pseudo("Coach-admin2")
				.nom("Coach")
				.prenom("ENI")
				.email("coachadmin2@campus-eni.fr")
				.motDePasse("000")
				.admin(true)
				.adresse(adresse2)
				.build();
		Utilisateur utilisateur3 = Utilisateur
				.builder()
				.pseudo("Coach-admin3")
				.nom("Coach")
				.prenom("ENI")
				.email("coachadmin3@campus-eni.fr")
				.motDePasse("000")
				.admin(true)
				.adresse(adresse3)
				.build();
		
		Categorie categorie = Categorie 
				.builder()
				.libelle("Informatique")
				.build();
		Categorie categorie2 = Categorie 
				.builder()
				.libelle("Informatique2")
				.build();
		Categorie categorie3 = Categorie 
				.builder()
				.libelle("Informatique3")
				.build();
		
		ArticleAVendre articleAVendre = ArticleAVendre
				.builder()
				.nom("Ordinateur Portable")
				.description("Ordinateur portable en bon état de marche")
				.photo(1)
				.dateDebutEncheres(Date.valueOf("2024-09-08"))
				.dateFinEncheres(Date.valueOf("2024-10-10"))
				.prixInitial(10)
				.prixVente(null)
				.adresse(adresse)
				.categorie(categorie)
				.vendeur(utilisateur)
				.build();
		ArticleAVendre articleAVendre2 = ArticleAVendre
				.builder()
				.nom("Ordinateur Portable2")
				.description("Ordinateur portable en bon état de marche")
				.photo(2)
				.dateDebutEncheres(Date.valueOf("2024-09-08"))
				.dateFinEncheres(Date.valueOf("2024-10-10"))
				.prixInitial(10)
				.prixVente(null)
				.adresse(adresse2)
				.categorie(categorie2)
				.vendeur(utilisateur2)
				.build();
		ArticleAVendre articleAVendre3 = ArticleAVendre
				.builder()
				.nom("Ordinateur Portable3")
				.description("Ordinateur portable en bon état de marche")
				.photo(3)
				.dateDebutEncheres(Date.valueOf("2024-09-08"))
				.dateFinEncheres(Date.valueOf("2024-10-10"))
				.prixInitial(10)
				.prixVente(null)
				.adresse(adresse3)
				.categorie(categorie3)
				.vendeur(utilisateur3)
				.build();
		
		List<ArticleAVendre> lstArticleAVendre = new ArrayList<ArticleAVendre>();
		
		lstArticleAVendre.add(articleAVendre);
		lstArticleAVendre.add(articleAVendre2);
		lstArticleAVendre.add(articleAVendre3);
		
		lstArticleAVendre.forEach( e-> {
			entityManager.persist(e);
		});
		entityManager.flush();
	}
}
