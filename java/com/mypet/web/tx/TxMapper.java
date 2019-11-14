package com.mypet.web.tx;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

import com.mypet.web.brd.Article;
import com.mypet.web.usr.User;

@Repository
public interface TxMapper {
	@Insert(" INSERT INTO USER (UID, PWD, UNAME) VALUES (" + 
			"            #{uid}, #{pwd}, #{uname})")
	public void insertUser(User u);
	@Insert("INSERT INTO ARTICLE(ARTICLESEQ, IMG, UID, COMMENTS, MSG, CONTENT, CATEGORY, RATING) VALUES ("
			+ "	#{articleseq}, #{img}, #{uid}, #{comments}, #{msg}, #{content}, #{category}, #{rating})")
	public void insertArticles(Article a);
}
