package com.mypet.web.brd;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

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
import org.springframework.web.multipart.MultipartFile;

import com.mypet.web.enums.Path;
import com.mypet.web.enums.SQL;
import com.mypet.web.pxy.Box;
import com.mypet.web.pxy.FileProxy;
import com.mypet.web.pxy.PageProxy;
import com.mypet.web.pxy.Proxy;
import com.mypet.web.pxy.Trunk;
import com.mypet.web.utl.Printer;

@RestController
@RequestMapping("/articles")

public class ArticleCtrl {
	private static final Logger Logger = LoggerFactory.getLogger(ArticleCtrl.class);
	@Autowired Article article;
	@Autowired ArticleMapper articleMapper;
	@Autowired Printer printer;
	@Autowired Box<Article> box;
	@Autowired Trunk<Object> trunk;
	@Autowired PageProxy pager;
	@Autowired Proxy proxy;
	@Autowired FileProxy filemgr;
	@PostMapping("/")
	public Map<?,?> write(@RequestBody Article param){
		param.setCategory("게시판");
		Consumer<Article> c = t -> articleMapper.insertArticle(param);
		c.accept(param);
		Supplier<Integer> s = () -> articleMapper.countArticle();
		trunk.put(Arrays.asList("msg", "count"), Arrays.asList("Success", s.get()));
		return trunk.get();
	}
	@GetMapping("/{articleseq}")
	public Article read(@PathVariable @RequestBody Article param){
		return null;
	}
	
	@PutMapping("/{articleseq}")
	public Article editArticle(@PathVariable String articleseq, @RequestBody Article param){
		box.clear();
		Consumer<Article> c = o-> articleMapper.editArticle(o);
		c.accept(param);
		Function<Article, Article> f = t-> articleMapper.getArticle(t);
		return f.apply(param);
	}
	@DeleteMapping("/{articleseq}")
	public Map<?,?> deleteArticle(@PathVariable String articleseq, @RequestBody Article param){
		Consumer<Article> c = o-> articleMapper.deleteArticle(o);
		trunk.put(Arrays.asList("msg"), Arrays.asList("Success"));
		c.accept(param);
		return trunk.get();
	}
	@GetMapping("/count")
	public Map<?,?> countNum() {
		Supplier<Integer> s = () -> articleMapper.countArticle();
		trunk.put(Arrays.asList("count"), Arrays.asList(s.get()));
		return trunk.get();
	}
	@GetMapping("/page/{pageNo}/size/{pageSize}")
	public Map<?,?> list(@PathVariable String pageNo,
			@PathVariable String pageSize) {
		System.out.println("넘어온 페이지 넘버: "+pageNo);
		pager.setPageNum(proxy.integer(pageNo));
		pager.setPageSize(proxy.integer(pageSize));
		pager.paging();
		Supplier<List<Article>> s =()-> articleMapper.selectAll(pager);
		trunk.put(Arrays.asList("articles","pxy"), Arrays.asList(s.get(),pager));
		return trunk.get();
	}
	@PostMapping("/fileupload")
	public void fileUpload(MultipartFile[] uploadFile) {
		filemgr.fileUpload(uploadFile);
	}
	@GetMapping("/create/table")
	public Map<?,?> createArticle() {
		HashMap<String, String> paramMap = new HashMap<>();
		paramMap.put("CREATE_ARTICLE", SQL.CREATE_ARTICLE.toString());
		printer.accept("테이블 생성쿼리 :::" + paramMap.get("CREATE_ARTICLE"));
		Consumer<HashMap<String, String>> c = o -> articleMapper.createArticle(o);
		c.accept(paramMap);
		paramMap.clear();
		paramMap.put("msg", "Success");
		return paramMap;
	}
}
