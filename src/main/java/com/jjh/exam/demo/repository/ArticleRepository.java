package com.jjh.exam.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.jjh.exam.demo.vo.Article;

@Mapper
public interface ArticleRepository {
	
	public Article getForPrintArticle(@Param("id") int id);	

	public List<Article> getForPrintArticles(@Param("boardId") int boardId, int limitStart, int limitTake, String searchKeywordTypeCode, String searchKeyword);

	public void writeArticle(@Param("memberId")int memberId, @Param("boardId") int boardId, @Param("title") String title, @Param("body") String body);

	public void deleteArticle(@Param("id") int id) ;

	public void modifyArticle(@Param("id")int id, @Param("title")String title, @Param("body")String body);

	public int getLastInsertId();

	public int getArticlesCount(int boardId, String searchKeywordTypeCode, String searchKeyword);

	public int increaseHitCount(int id);

	public int getArticleHitCount(int id);

	public int increseGoodReactionPoint(int relId);

	public int increseBadReactionPoint(int relId);

	public int decreseGoodReactionPoint(int relId);

	public int decreseBadReactionPoint(int relId);

}






