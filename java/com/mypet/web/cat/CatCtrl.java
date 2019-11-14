package com.mypet.web.cat;

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
import com.mypet.web.enums.SQL;
import com.mypet.web.utl.Printer;


@RestController
@RequestMapping("/cat")

public class CatCtrl {
	private static final Logger Logger = LoggerFactory.getLogger(CatCtrl.class);
	@Autowired Map<String, Object> map;
	@Autowired Cat cat;
	@Autowired Printer printer;
	@Autowired CatMapper catMapper;
	@PostMapping("/")
	public Map<?,?> register(@RequestBody Cat param) {
//		Logger.info("AJAX가 보낸 아이디 & 비번 {}", param.getUid()+", "+ param.getPwd()+", "+ param.getUname());
		printer.accept("람다 프린터가 출력한 값 "+ param.getBirth()+", "+ param.getSex()+", "+ param.getName()
		+", "+ param.getHairlength()+", "+ param.getHaircolor()+", "+ param.getSpecies()+", "+ param.getSize()+", "+ param.getCharacter()+", "+ param.getIllhistory());
		Consumer<Cat> c = o -> catMapper.registerCat(param);
		c.accept(param);
		map.clear();
		map.put("msg", "Success");
		return map;
	}
	@PutMapping("/update/cat")
	public  Map<?,?> updatecat(@RequestBody Cat param) {
		Consumer<Cat> c = o->catMapper.updateCat(param);
		c.accept(param);
		map.clear();
		map.put("msg", "Success");
		return map;
	}
	@DeleteMapping("/erase/cat")
	public Map<?,?> eraseCat(@RequestBody Cat param) {
		Consumer<Cat> c = o->catMapper.dropCat(param);
		c.accept(param);
		map.clear();
		map.put("msg", "Delete Success");
		return map;
	}
	@GetMapping("/create/table")
	public Map<?,?> createCat() {
		HashMap<String, String> paramMap = new HashMap<>();
		paramMap.put("CREATE_CAT", SQL.CREATE_CAT.toString());
		printer.accept("테이블 생성쿼리 :::" + paramMap.get("CREATE_CAT"));
		Consumer<HashMap<String, String>> c = o -> catMapper.createCat(o);
		c.accept(paramMap);
		paramMap.clear();
		paramMap.put("msg", "Success");
		return paramMap;
	}
	@GetMapping("/drop/table")
	public Map<?,?> dropCat() {
		HashMap<String, String> paramMap = new HashMap<>();
		paramMap.put("DROP_CAT", SQL.DROP_CAT.toString());
		printer.accept("테이블 삭제쿼리 :::" + paramMap.get("DROP_CAT"));
		Consumer<HashMap<String, String>> c = o -> catMapper.createCat(o);
		c.accept(paramMap);
		paramMap.clear();
		paramMap.put("msg", "Success");
		return paramMap;
	}
}