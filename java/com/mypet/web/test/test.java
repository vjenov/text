package com.mypet.web.test;

import java.util.List;

public class test {
	private static List<Integer> pages;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int blockNum = 3;
		int pageCount = 38;
		
		if(blockNum+1==8) {
    		for(int i=5-1; i>=0; i--){
    			pages.add(pageCount-i);
    		}
    	}else {
    		for(int j=1; j<=5; j++){
    			pages.add(5*blockNum+j);
    		}
    	}
	}

}
