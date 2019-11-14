package com.mypet.web.tx;

import java.util.Arrays;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mypet.web.pxy.Box;
import com.mypet.web.pxy.CrawlingProxy;
import com.mypet.web.pxy.Trunk;
import com.mypet.web.utl.Printer;

@RestController
@RequestMapping("/tx")
@Transactional
public class TxController {
	@Autowired TxService txService;
	@Autowired Printer printer;
	@Autowired Trunk<String> trunk;
	@Autowired CrawlingProxy crawler;
	@Autowired Box<String> box;
	@GetMapping("/crawling/{site}/{srch}")
	public void bringUrl(@PathVariable String site, @PathVariable String srch) {
		printer.accept(site +", srch" + srch);
		trunk.put(Arrays.asList("site", "srch"), Arrays.asList(site, srch));
		txService.crawling(trunk.get());
	}
	@GetMapping("/register/users")
	public Map<?,?> registerUsers() {
		int userCount = txService.registerUsers();
		printer.accept("서비스 카운팅 :::" + userCount);
		trunk.put(Arrays.asList("userCount"), Arrays.asList(crawler.string(userCount)));
		return trunk.get();
	}
	@GetMapping("/truncate/users")
	public Map<?,?> truncateUsers() {
		int userCount = txService.truncateUsers();
		printer.accept("서비스 카운팅: "+ userCount);
		trunk.put(Arrays.asList("userCount"), Arrays.asList(crawler.string(userCount)));
		return trunk.get();
	}
	@GetMapping("/write/articles")
	public Map<?,?> writeArticles() {
		int userCount = txService.writeArticles();
		printer.accept("서비스 카운팅 :::" + userCount);
		trunk.put(Arrays.asList("userCount"), Arrays.asList(crawler.string(userCount)));
		return trunk.get();
	}
}
