package fr.eni.encheres.bo;

import java.time.LocalDateTime;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class Enchere {
	private LocalDateTime date;
	private int montant;
	
	// Associations
	private ArticleAVendre articleAVendre;
	private Utilisateur acquereur;
}
