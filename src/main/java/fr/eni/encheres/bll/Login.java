package fr.eni.encheres.bll;

import org.springframework.beans.factory.annotation.Autowired;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.UtilisateurRepository;

public class Login {

	@Autowired
	UtilisateurRepository utilisateurRepository;
	
	public boolean verifyPseudo(String pseudo, String password) {
		
		Utilisateur utilisateur = utilisateurRepository.findOneByPseudo(pseudo);
		
		if (utilisateur == null) {
			return false;
		}
		
		// TODO Add spring boot security to verify password for connection
		String hashedPassword = "";
		if (hashedPassword != utilisateur.getMotDePasse()) {
			return false;
		}
		
		return true;
	}
}
