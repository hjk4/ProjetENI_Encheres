package fr.eni.encheres.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.eni.encheres.bo.ArticleAVendre;

public interface ArticleAVendreRepository extends JpaRepository<ArticleAVendre, Long>{

}
