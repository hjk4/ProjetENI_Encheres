package fr.eni.encheres.bo;

import java.time.LocalDate;
import java.util.List;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class ArticleAVendre {
	private long id;
	private String nom;
	private String description;
	private LocalDate dateDebutEncheres;
	private LocalDate dateFinEncheres;
	private int statut;
	private int prixInitial;
	private int prixVente;
	private List<String> listePhoto;
	
	// Associations
	private Categorie catgorie;
	private Adresse adresse;
	private Utilisateur vendeur;
}
