package fr.eni.encheres.bll.afficher;

import org.springframework.beans.factory.annotation.Autowired;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.UtilisateurRepository;

public class RecupererProfil {
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	public Utilisateur afficherProfilbyPseudo(String pseudo) {
		return utilisateurRepository.findOneByPseudo(pseudo);	
	}
	
}
