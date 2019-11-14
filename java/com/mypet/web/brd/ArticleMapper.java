package com.mypet.web.brd;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mypet.web.pxy.PageProxy;

@Repository
public interface ArticleMapper {
	public void insertArticle(Article param);
	public Article getArticle(Article param);
	public void editArticle(Article param);
	public void deleteArticle(Article param);
	public int countArticle();
	public List<Article> selectAll(PageProxy pxy);
	public void createArticle(HashMap<String, String> paramMap);
}
