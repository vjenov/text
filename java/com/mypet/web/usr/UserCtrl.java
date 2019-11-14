package com.mypet.web.usr;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mypet.web.cmm.IConsumer;
import com.mypet.web.cmm.IFunction;
import com.mypet.web.enums.SQL;
import com.mypet.web.utl.Printer;


@RestController
@RequestMapping("/users")

public class UserCtrl {
	private static final Logger Logger = LoggerFactory.getLogger(UserCtrl.class);
	@Autowired Map<String, Object> map;
	@Autowired User user;
	@Autowired Printer printer;
	@Autowired UserMapper userMapper;
	
	@GetMapping("/{uid}/existId")
	public Map<?,?> existId(@PathVariable String uid){
		IFunction<String, Integer> f = t-> userMapper.existId(uid);
		map.clear();
		map.put("msg", (f.apply(uid)==0) ? "Success" : "Already Exist");
		return map;
	}
	@PostMapping("/")
	public Map<?,?> join(@RequestBody User param) {
//		Logger.info("AJAX가 보낸 아이디 & 비번 {}", param.getUid()+", "+ param.getPwd()+", "+ param.getUname());
		printer.accept("람다 프린터가 출력한 값 "+ param.getUid()+", "+ param.getPwd());
		IConsumer<User> c = o -> userMapper.insertUser(param);
		c.accept(param);
		map.clear();
		map.put("msg", "Success");
		return map;
	}
	@PostMapping("/{uid}")
	public User login(@PathVariable String uid, @RequestBody User param) {
		/*
		 * return new IFunction() {
		 * 
		 * @Override public Object apply(Object o) { // TODO Auto-generated method stub
		 * return userMapper.selectByIdPw(param); } }.apply(param);
		 */
		
		/*
		 * IFunction f = o -> userMapper.selectByIdPw(param); return (User)
		 * f.apply(param);
		 */
		
		IFunction<User, User> f = t -> userMapper.selectByIdPw(param);
		return f.apply(param);
	}
	@GetMapping("/{uid}")
	public User searchUserById(@PathVariable String uid, @RequestBody User param) {
		IFunction<User, User> f = t -> userMapper.findById(param);
		return f.apply(param);
	}
	@PutMapping("/{uid}")
	public  Map<?,?> updateUser(@PathVariable String uid, @RequestBody User param) {
		IConsumer<User> c = o->userMapper.updateUser(param);
		c.accept(param);
		map.clear();
		map.put("msg", "Success");
		return map;
	}
	@DeleteMapping("/{uid}")
	public Map<?,?> removeUser(@PathVariable String uid, @RequestBody User param) {
		IConsumer<User> c = o->userMapper.deleteUser(param);
		c.accept(param);
		map.clear();
		map.put("msg", "Delete Success");
		return map;
	}
	@GetMapping("/create/table")
	public Map<?,?> createUser() {
		HashMap<String, String> paramMap = new HashMap<>();
		paramMap.put("CREATE_USER", SQL.CREATE_USER.toString());
		printer.accept("테이블 생성쿼리 :::" + paramMap.get("CREATE_USER"));
		Consumer<HashMap<String, String>> c = o -> userMapper.createUser(o);
		c.accept(paramMap);
		paramMap.clear();
		paramMap.put("msg", "Success");
		return paramMap;
	}
	@GetMapping("/drop/table")
	public Map<?,?> dropUser() {
		HashMap<String, String> paramMap = new HashMap<>();
		paramMap.put("DROP_USER", SQL.DROP_USER.toString());
		printer.accept("테이블 삭제쿼리 :::" + paramMap.get("DROP_USER"));
		Consumer<HashMap<String, String>> c = o -> userMapper.createUser(o);
		c.accept(paramMap);
		paramMap.clear();
		paramMap.put("msg", "Success");
		return paramMap;
	}
}