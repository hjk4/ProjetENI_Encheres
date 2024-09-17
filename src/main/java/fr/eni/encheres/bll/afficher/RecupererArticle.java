package fr.eni.encheres.bll.afficher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import fr.eni.encheres.bo.ArticleAVendre;
import fr.eni.encheres.dal.ArticleAVendreRepository;

public class RecupererArticle {
	
	@Autowired
	private ArticleAVendreRepository articleAVendreRepository;

	public ArticleAVendre recupererArticleAVendreById(long id) {
		return articleAVendreRepository.findOneById(id);
	}
	
	public List<ArticleAVendre> repurerTous() {
		return articleAVendreRepository.findAll();
	}
}
