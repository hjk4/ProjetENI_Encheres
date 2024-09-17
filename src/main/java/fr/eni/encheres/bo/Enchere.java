package fr.eni.encheres.bo;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder

@Entity
@Table(name = "ENCHERES")
@IdClass(EnchereId.class)
public class Enchere {
	// Associations
	@Id
	@OneToOne
	@JoinColumn(name = "id_utilisateur", nullable = false)
	private Utilisateur acquereur;
	
	@Id
	@OneToOne
	@JoinColumn(name = "no_article", nullable = false)
	private ArticleAVendre articleAVendre;
	
	// Propre
	@Column(name = "date_enchere", nullable = false)
	private LocalDateTime date;
	
	@Id
	@Column(name = "montant_enchere", nullable = false)
	private int montant;
	
}
