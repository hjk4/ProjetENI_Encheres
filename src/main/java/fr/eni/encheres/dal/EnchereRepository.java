package fr.eni.encheres.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.EnchereId;

public interface EnchereRepository extends JpaRepository<Enchere, EnchereId>{

}
