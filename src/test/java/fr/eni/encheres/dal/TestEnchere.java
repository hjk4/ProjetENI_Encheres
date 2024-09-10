package fr.eni.encheres.dal;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.MethodName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import fr.eni.encheres.bo.Adresse;
import fr.eni.encheres.bo.ArticleAVendre;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.EnchereId;
import fr.eni.encheres.bo.Utilisateur;
import lombok.extern.slf4j.Slf4j;

@DataJpaTest
@Slf4j
@TestMethodOrder(MethodName.class)
public class TestEnchere {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private EnchereRepository enchereRepository;
	
	@Autowired 
	private ArticleAVendreRepository articleAVendreRepository;
	// TODO Modify the tests to avoid using cascades
	
	@Test
	public void test01_enchere_save() {
		
		Adresse adresse = Adresse
				.builder()
				.ville("Ville")
				.codePostal("78154")
				.rue("10 Rue des églantiers")
				.adresseEni(false)
				.build();
		
		//adresseRepository.save(adresse);
		
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
		
		//utilisateurRepository.save(utilisateur);
		
		Categorie categorie = Categorie 
				.builder()
				.libelle("Informatique")
				.build();
		
		//categorieRepository.save(categorie);
		
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
		
		//articleAVendre.setCategorie(categorie);
		//articleAVendre.setAdresse(adresse);
		//articleAVendre.setVendeur(utilisateur);
		
		articleAVendreRepository.save(articleAVendre);
		
		Enchere enchere = Enchere
									.builder()
									.acquereur(utilisateur)
									.date(LocalDateTime.now())
									.montant(50)
									.build();
		
		enchere.setArticleAVendre(articleAVendre);
		
		Enchere enchereSauver = enchereRepository.save(enchere);
		
		log.info(enchereSauver.toString());
		assertThat(enchereSauver.getAcquereur()).isEqualTo(enchere.getAcquereur());
		assertThat(enchereSauver.getArticleAVendre()).isEqualTo(enchere.getArticleAVendre());
		assertThat(enchereSauver.getDate()).isEqualTo(enchere.getDate());
		assertThat(enchereSauver.getMontant()).isEqualTo(enchere.getMontant());							
	}
	
	@Test
	public void test02_enchere_findById() {
		Adresse adresse = Adresse
				.builder()
				.ville("Ville")
				.codePostal("78154")
				.rue("10 Rue des églantiers")
				.adresseEni(false)
				.build();
		
		Utilisateur acquereur = Utilisateur
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
				.build();
		
		articleAVendre.setCategorie(categorie);
		articleAVendre.setAdresse(adresse);
		articleAVendre.setVendeur(acquereur);
		
		Enchere enchere = Enchere
									.builder()
									.acquereur(acquereur)
									.date(LocalDateTime.now())
									.montant(50)
									.build();
		
		enchere.setArticleAVendre(articleAVendre);
		
		Enchere enchereSauver = entityManager.persist(enchere);
		entityManager.flush();
		log.info(enchereSauver.toString());
		assertThat(enchereSauver.getAcquereur()).isEqualTo(enchere.getAcquereur());
		assertThat(enchereSauver.getArticleAVendre()).isEqualTo(enchere.getArticleAVendre());
		assertThat(enchereSauver.getDate()).isEqualTo(enchere.getDate());
		assertThat(enchereSauver.getMontant()).isEqualTo(enchere.getMontant());	
		
		EnchereId enchereId = EnchereId 
						.builder()
						.acquereur(acquereur)
						.articleAVendre(articleAVendre)
						.montant(enchere.getMontant())
						.build();
		
		Optional<Enchere> op = enchereRepository.findById(enchereId);
		assertThat(op.isPresent()).isTrue();
		Enchere enchereDB = op.get();
		assertThat(enchereDB.getAcquereur()).isEqualTo(enchere.getAcquereur());
		assertThat(enchereDB.getArticleAVendre()).isEqualTo(enchere.getArticleAVendre());
		assertThat(enchereDB.getDate()).isEqualTo(enchere.getDate());
		assertThat(enchereDB.getMontant()).isEqualTo(enchere.getMontant());	
		log.info("Fecthed entite : " + enchereDB.toString());
		
	}
	
	@Test
	public void test03_enchere_update() {
		Adresse adresse = Adresse
				.builder()
				.ville("Ville")
				.codePostal("78154")
				.rue("10 Rue des églantiers")
				.adresseEni(false)
				.build();
		
		Utilisateur acquereur = Utilisateur
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
				.build();
		
		articleAVendre.setCategorie(categorie);
		articleAVendre.setAdresse(adresse);
		articleAVendre.setVendeur(acquereur);
		
		Enchere enchere = Enchere
									.builder()
									.acquereur(acquereur)
									.date(LocalDateTime.now())
									.montant(50)
									.build();
		
		enchere.setArticleAVendre(articleAVendre);
		
		entityManager.persist(enchere);
		entityManager.flush();
		log.info("Avant update :" + enchere.toString());
		
		enchere.setMontant(60);
		enchere.setDate(LocalDateTime.now());
		
		Enchere enchereUpdate = enchereRepository.save(enchere);
		assertThat(enchereUpdate.getAcquereur()).isEqualTo(enchere.getAcquereur());
		assertThat(enchereUpdate.getArticleAVendre()).isEqualTo(enchere.getArticleAVendre());
		assertThat(enchereUpdate.getDate()).isEqualTo(enchere.getDate());
		assertThat(enchereUpdate.getMontant()).isEqualTo(enchere.getMontant());	
		log.info("Apres update : " + enchereUpdate.toString());
	}
	
	@Test
	public void test04_enchere_delete() {
		Adresse adresse = Adresse
				.builder()
				.ville("Ville")
				.codePostal("78154")
				.rue("10 Rue des églantiers")
				.adresseEni(false)
				.build();
		
		Utilisateur acquereur = Utilisateur
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
				.build();
		
		articleAVendre.setCategorie(categorie);
		articleAVendre.setAdresse(adresse);
		articleAVendre.setVendeur(acquereur);
		
		Enchere enchere = Enchere
									.builder()
									.acquereur(acquereur)
									.date(LocalDateTime.now())
									.montant(50)
									.build();
		
		enchere.setArticleAVendre(articleAVendre);
		
		entityManager.persist(enchere);
		entityManager.flush();
		log.info(enchere.toString());
		
		enchereRepository.delete(enchere);
		
		EnchereId enchereId = EnchereId 
				.builder()
				.acquereur(acquereur)
				.articleAVendre(articleAVendre)
				.montant(enchere.getMontant())
				.build();
		
		Enchere enchereDel = entityManager.find(Enchere.class, enchereId);
		ArticleAVendre articleAVendreNotDel = entityManager.find(ArticleAVendre.class, articleAVendre.getId());
		assertThat(enchereDel).isNull();
		assertThat(articleAVendreNotDel).isNotNull();
	}
	
	@Test
	public void test05_enchere_findAllEmpty() {
		List<Enchere> lstEnchere = enchereRepository.findAll();
		assertThat(lstEnchere).isEmpty();
	}
	
	@Test
	public void test06_enchere_findAll() {
		jeuDeDonnees();
		
		List<Enchere> Encheres = enchereRepository.findAll();
		assertThat(Encheres).isNotEmpty();
		assertThat(Encheres.size()).isGreaterThan(1);
		
		Encheres.forEach( e -> {
			log.info(e.toString());
		});
	}
	
	private void jeuDeDonnees() {
		List<Enchere> lstEnchere = new ArrayList<>();
		
		Adresse adresse = Adresse
				.builder()
				.ville("Ville")
				.codePostal("78154")
				.rue("10 Rue des églantiers")
				.adresseEni(false)
				.build();
		
		Utilisateur acquereur = Utilisateur
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
				.build();
		
		articleAVendre.setCategorie(categorie);
		articleAVendre.setAdresse(adresse);
		articleAVendre.setVendeur(acquereur);
		
		Enchere enchere = Enchere
									.builder()
									.acquereur(acquereur)
									.date(LocalDateTime.now())
									.montant(50)
									.build();
		
		enchere.setArticleAVendre(articleAVendre);
		
		
		//Enchere2
		Adresse adresse2 = Adresse
				.builder()
				.ville("Ville2")
				.codePostal("78154")
				.rue("10 Rue des églantiers")
				.adresseEni(false)
				.build();
		
		Utilisateur acquereur2 = Utilisateur
				.builder()
				.pseudo("Coach-admin2")
				.nom("Coach")
				.prenom("ENI")
				.email("coachadmin2@campus-eni.fr")
				.motDePasse("000")
				.admin(true)
				.adresse(adresse2)
				.build();
		
		Categorie categorie2 = Categorie 
				.builder()
				.libelle("Meuble")
				.build();
		
		ArticleAVendre articleAVendre2 = ArticleAVendre
				.builder()
				.nom("Table")
				.description("Table en bois")
				.photo(1)
				.dateDebutEncheres(Date.valueOf("2024-09-08"))
				.dateFinEncheres(Date.valueOf("2024-10-10"))
				.prixInitial(10)
				.prixVente(null)
				.build();
		
		articleAVendre2.setCategorie(categorie2);
		articleAVendre2.setAdresse(adresse2);
		articleAVendre2.setVendeur(acquereur2);
		
		Enchere enchere2 = Enchere
									.builder()
									.acquereur(acquereur2)
									.date(LocalDateTime.now())
									.montant(50)
									.build();
		
		enchere2.setArticleAVendre(articleAVendre2);
		
		//Enchere3
		
		Adresse adresse3 = Adresse
				.builder()
				.ville("Ville")
				.codePostal("78154")
				.rue("10 Rue des églantiers")
				.adresseEni(false)
				.build();
		
		Utilisateur acquereur3 = Utilisateur
				.builder()
				.pseudo("Coach-admin3")
				.nom("Coach")
				.prenom("ENI")
				.email("coachadmin3@campus-eni.fr")
				.motDePasse("000")
				.admin(true)
				.adresse(adresse3)
				.build();
		
		Categorie categorie3 = Categorie
				.builder()
				.libelle("Electronique")
				.build();
		
		ArticleAVendre articleAVendre3 = ArticleAVendre
				.builder()
				.nom("Ordinateur Portable")
				.description("Ordinateur portable en bon état de marche")
				.photo(1)
				.dateDebutEncheres(Date.valueOf("2024-09-08"))
				.dateFinEncheres(Date.valueOf("2024-10-10"))
				.prixInitial(10)
				.prixVente(null)
				.categorie(categorie3)
				.build();
		
		articleAVendre3.setAdresse(adresse3);
		articleAVendre3.setVendeur(acquereur3);
		
		Enchere enchere3 = Enchere
									.builder()
									.acquereur(acquereur3)
									.date(LocalDateTime.now())
									.montant(50)
									.build();
		
		enchere3.setArticleAVendre(articleAVendre3);
		
		lstEnchere.add(enchere);
		lstEnchere.add(enchere2);
		lstEnchere.add(enchere3);
		
		lstEnchere.forEach(e -> {
			entityManager.persist(e);
		});
		
		entityManager.flush();
	}
	
}
