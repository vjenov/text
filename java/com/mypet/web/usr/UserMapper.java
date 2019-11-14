package com.mypet.web.usr;

import java.util.HashMap;

import org.springframework.stereotype.Repository;
@Repository
public interface UserMapper {
	public void insertUser(User user);
	public User selectByIdPw(User user);
	public User findById(User user);
	public User updateUser(User user);
	public User deleteUser(User user);
	public int existId(String uid);
	public int countUsers();
	public void createUser(HashMap<String, String> paramMap);
	public void dropUser(HashMap<String, String> paramMap);
	public void createDB(HashMap<String, String> paramMap);
	public void truncateUser(HashMap<String, String> paramMap);
}
