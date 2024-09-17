package fr.eni.encheres.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.eni.encheres.bo.Adresse;

@Repository
public interface AdresseRepository extends JpaRepository<Adresse, Long>{

}
