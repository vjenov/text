package com.mypet.web.cat;

import java.util.HashMap;

import org.springframework.stereotype.Repository;
@Repository
public interface CatMapper {
	public void registerCat(Cat user);
	public Cat updateCat(Cat user);
	public Cat dropCat(Cat user);
	public int existId(String uid);
	public int countUsers();
	public void createCat(HashMap<String, String> paramMap);
	public void dropCat(HashMap<String, String> paramMap);
}
