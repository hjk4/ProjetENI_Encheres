package fr.eni.encheres.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.eni.encheres.bo.Categorie;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long>{

}
