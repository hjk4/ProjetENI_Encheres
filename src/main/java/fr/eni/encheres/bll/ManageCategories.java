package fr.eni.encheres.bll;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dal.CategorieRepository;

public class ManageCategories {
	
	@Autowired
	private CategorieRepository categorieRepository;
	
	//TODO Add error propagation
	public boolean createCategorie(Categorie categorie) {
		Categorie categorieDB = categorieRepository.save(categorie);
		if (categorieDB.getId() == 0) {
			return false;
		}
		return true;
	}
	
	//TODO Add error propagation
	public boolean modifyCategorie(Categorie categorie) {
		Optional<Categorie> original = categorieRepository.findById(categorie.getId());
		if (original == null) {
			return false;
		}
		Categorie originalCat = original.get();
		
		originalCat.setLibelle(categorie.getLibelle());
		
		Categorie nouvCat = categorieRepository.save(originalCat);
		if (nouvCat.getLibelle() != categorie.getLibelle()) {
			return false;
		}
		return true;
	}
	
	//TODO Add error propagation
	public boolean deleteCategorie(long categorieId) {
		categorieRepository.deleteById(categorieId);
		return true;
	}
	
}
