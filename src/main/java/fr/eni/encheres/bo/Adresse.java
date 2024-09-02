package fr.eni.encheres.bo;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class Adresse {
	private long id;
	private String rue;
	private String codePostal;
	private String ville;
}
