package com.mypet.web.enums;

public enum Path {
	UPLOAD_PATH, CRAWLING_TARGET;
	public String toString() {
		String result = "";
		switch(this) {
		case UPLOAD_PATH :
			result = "C:\\Users\\user\\git\\EGOV_CATDOG\\src\\main\\webapp\\resources\\upload\\";
			break;
		case CRAWLING_TARGET :
			result = "https://store.naver.com/accommodations/detail?entry=plt&id=1285629759&tab=bookingReview&tabPage=";
		}
		return result;
	}
}
