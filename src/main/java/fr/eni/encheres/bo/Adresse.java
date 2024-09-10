package fr.eni.encheres.bo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder

@Entity
@Table(name = "ADRESSES")
public class Adresse {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "no_adresse")
	private long id;
	
	@Column(name = "rue", length = 100, nullable = false)
	private String rue;
	
	@Column(name = "code_postal", length = 10, nullable = false)
	private String codePostal;
	
	@Column(name = "ville", length = 50, nullable = false)
	private String ville;
	
	@Column(name = "adresse_eni", nullable = false, columnDefinition = "BIT default 0")
	private boolean adresseEni;
}
