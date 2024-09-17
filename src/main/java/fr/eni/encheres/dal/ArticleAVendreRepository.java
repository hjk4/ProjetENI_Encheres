package fr.eni.encheres.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.eni.encheres.bo.ArticleAVendre;

@Repository
public interface ArticleAVendreRepository extends JpaRepository<ArticleAVendre, Long>{

	ArticleAVendre findOneById(long id);
}
