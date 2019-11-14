package com.mypet.web.test;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {
	public static void main(String[] args) {
		try {
			Document rawData = Jsoup.connect("https://map.naver.com/v5/search/%EC%95%A0%EC%99%84%EB%8F%99%EB%AC%BC%20%EC%B9%B4%ED%8E%98?c=14132363.3339608,4516838.3733418,14,0,0,0,dh").timeout(10*1000).get();
			Elements title = rawData.select("span[class=search_title_text]");
			Elements address = rawData.select("span[class=search_text address]");
			
			
			Elements artist = rawData.select("aside[class=recommendReview] p");
//			Elements title = rawData.select("p[class=title]");
//			List<String> artist2 = new ArrayList<>();
			List<String> title2 = new ArrayList<>();
			List<String> address2 = new ArrayList<>();
			for(Element e : title) {
				title2.add(e.text());
			}
			for(Element e : address) {
				address2.add(e.text());
			}
			/*
			 * for(Element e : title) { title2.add(e.text()); }
			 */
			System.out.println(title2);
			System.out.println("-----------------------------------");
			System.out.println(address2);
		}catch(Exception e2) {
			e2.printStackTrace();
		}
	}
}
