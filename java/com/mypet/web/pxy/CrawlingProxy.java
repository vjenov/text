package com.mypet.web.pxy;

import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mypet.web.enums.Path;
import com.mypet.web.utl.Printer;
@Component("crawler")
public class CrawlingProxy extends Proxy{
	@Autowired Printer p;
	@Autowired Box<String> box;
	public Box<String> choose(Map<?, ?> paramMap) {
		p.accept("키값" + paramMap.get("site"));
		p.accept("키값" + paramMap.get("srch"));;
		switch(string(paramMap.get("srch"))) {
		case "스톤애견풀빌라" :
			p.accept(Path.CRAWLING_TARGET.toString()+"1");
			box = crawling(Path.CRAWLING_TARGET.toString()+"1");
			break;
		default :
			crawling("http://"+paramMap.get("site")+"/");
		}
	    return box;
	}
	private Box<String> crawling(String url) {
		p.accept("넘어온 URL \n"+url);
		try {
			Document rawData = Jsoup.connect(url).timeout(10*1000).get();
			Elements title = rawData.select("div[class=review_txt]");
			for(Element e : title) {
				box.add(e.text() + "::::: \n");
			}
			System.out.println(box);
		}catch(Exception e2) {
			e2.printStackTrace();
		}
		return box;
	}
}
