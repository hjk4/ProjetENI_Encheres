package fr.eni.encheres.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, String>{

}
