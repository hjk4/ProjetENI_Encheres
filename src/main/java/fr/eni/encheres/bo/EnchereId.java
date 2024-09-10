package fr.eni.encheres.bo;

import java.io.Serializable;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder

public class EnchereId implements Serializable{
	/**
	 * Default
	 */
	private static final long serialVersionUID = 1L;
	
	private Utilisateur acquereur;
	private ArticleAVendre articleAVendre;
	private int montant;
}
