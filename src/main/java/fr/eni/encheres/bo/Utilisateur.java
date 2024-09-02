package fr.eni.encheres.bo;

import java.util.List;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class Utilisateur {
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private String motDePasse;
	private int credit;
	private boolean admin;
	
	// Associations
	private Adresse adresse;
	private List<ArticleAVendre> lstArticlesAVendre;
}
