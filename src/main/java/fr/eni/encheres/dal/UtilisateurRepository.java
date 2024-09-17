package fr.eni.encheres.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.eni.encheres.bo.Utilisateur;


@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, String>{

	Utilisateur findOneByPseudo(String pseudo);
	Utilisateur findOneByEmail(String email);
}
