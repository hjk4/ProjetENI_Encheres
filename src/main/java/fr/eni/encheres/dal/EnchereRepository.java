package fr.eni.encheres.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.EnchereId;

@Repository
public interface EnchereRepository extends JpaRepository<Enchere, EnchereId>{

}
