package fr.eni.encheres.bll;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import fr.eni.encheres.bo.Adresse;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.AdresseRepository;
import fr.eni.encheres.dal.UtilisateurRepository;

public class CreateAccount {
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
	private AdresseRepository adresseRepository;
	
	// TODO Add error propagation
	public boolean AccountCreation(String pseudo, String nom, String prenom, String email, String telephone, String rue, String codePostal, String Ville, String motDePasse) {
		Adresse adresse = Adresse
				.builder()
				.rue(rue)
				.codePostal(codePostal)
				.ville(Ville)
				.build();
		
		Adresse adresseDB = adresseRepository.save(adresse);
		if (adresseDB == null)
			return false;
		
		//TODO Use org.springframework.security.crypto.factory.PasswordEncoderFactories to encrypt motDePasse in the BLL
		String motDePasseHashed = motDePasse;
		
		Utilisateur utilisateur = Utilisateur
				.builder()
				.pseudo(pseudo)
				.nom(prenom)
				.prenom(prenom)
				.email(email)
				.telephone(telephone)
				.motDePasse(motDePasseHashed)
				.adresse(adresse)
				.admin(false)
				.build();
		
		Utilisateur utilisateurDB = utilisateurRepository.save(utilisateur);
		if (utilisateurDB == null)
			return false;
		
		return true;
	}
	
	public boolean accountUnicityVerification(String pseudo, String email) {
		Utilisateur pseudoExists = utilisateurRepository.findOneByPseudo(pseudo);
		if(!(pseudoExists == null))
			return false;
		
		Utilisateur emailExists = utilisateurRepository.findOneByEmail(email);
		if(!(emailExists == null))
			return false;
		
		return true;
	}
	
	public static boolean isValidPassword(String motDePasse) {
		String passwordPattern = "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9_]{8,20}$";
		
		Pattern pattern = Pattern.compile(passwordPattern);
		
		return pattern.matcher(motDePasse).matches();
	}
}
