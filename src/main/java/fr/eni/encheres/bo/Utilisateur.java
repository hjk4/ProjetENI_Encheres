package fr.eni.encheres.bo;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString(exclude = "motDePasse")
@Builder

@Entity
@Table(name = "UTILISATEURS")
public class Utilisateur {
	
	@Id
	@Column(name = "pseudo", length = 30, nullable = false, unique = true)
	private String pseudo;
	
	@Column(name = "nom", length = 40, nullable = false)
	private String nom;
	
	@Column(name = "prenom", length = 50, nullable = false)
	private String prenom;
	
	@Column(name = "email", length = 100, nullable = false, unique = true)
	private String email;
	
	@Column(name = "telephone", length = 15, nullable = true)
	private String telephone;
	
	@Column(name = "mot_de_passe", length = 68, nullable = false)
	private String motDePasse;
	
	@Column(name = "credit", columnDefinition = "INT default 10" ,nullable = false)
	@Builder.Default
	private int credit = 10;
	
	@Column(name = "administrateur", nullable = false, columnDefinition = "BIT default 0")
	private boolean admin;
	// TODO Delete the cascade types
	// Associations
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE } ) //Cascade for testing purposes
	@JoinColumn(name = "no_adresse", nullable = false)
	private Adresse adresse;
	
	// Association hors DB
	//private List<ArticleAVendre> lstArticlesAVendre;
}
