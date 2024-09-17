package fr.eni.encheres.bo;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder

@Entity
@Table(name = "ARTICLES_A_VENDRE")
public class ArticleAVendre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "no_article", nullable = false)
	private long id;
	
	@Column(name = "nom_article", nullable = false)
	private String nom;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "photo", nullable = false)
	private int photo;
	
	@Column(name = "date_debut_encheres", nullable = false)
	private Date dateDebutEncheres;
	
	@Column(name = "date_fin_encheres", nullable = false)
	private Date dateFinEncheres;
	
	// statut (default 0 : pas commencee ; 1 : en cours ; 2 : cloturee ; 3 : livree ; 100 : annulee)
	@Column(name = "statut_enchere", columnDefinition = "INT default 0" ,nullable = false)
	private int statut;
	
	@Column(name = "prix_initial", nullable = false)
	private int prixInitial;
	
	@Column(name = "prix_vente")
	private Integer prixVente;

	// Associations
	@OneToOne
	@JoinColumn(name = "id_utilisateur", nullable = false)
	private Utilisateur vendeur;
	
	@OneToOne
	@JoinColumn(name = "no_categorie", nullable = false)
	private Categorie categorie;
	
	@OneToOne
	@JoinColumn(name = "no_adresse_retrait", nullable = false)
	private Adresse adresse;
	
}
