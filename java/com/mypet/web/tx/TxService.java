package com.mypet.web.tx;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mypet.web.brd.ArticleMapper;
import com.mypet.web.pxy.ArticleProxy;
import com.mypet.web.pxy.Box;
import com.mypet.web.pxy.CrawlingProxy;
import com.mypet.web.pxy.UserProxy;
import com.mypet.web.usr.UserMapper;

@Service
public class TxService {
	@Autowired TxMapper txMapper;
	@Autowired UserMapper userMapper;
	@Autowired CrawlingProxy crawler;
	@Autowired UserProxy manager;
	@Autowired ArticleProxy jeno;
	@Autowired ArticleMapper articleMapper;
	@Autowired Box<String> box;
	//@Autowired List<String> txServicelist;
	
	public Box<String> crawling(Map<?,?> paramMap){
		return crawler.choose(paramMap);
	}
	@Transactional
	public int registerUsers(){
		manager.insertUsers();
		return userMapper.countUsers();
	}
	public int truncateUsers() {
		manager.truncateUsers();
		return userMapper.countUsers();
	}
	public int writeArticles() {
		jeno.insertArticle();
		return articleMapper.countArticle();
	}
}