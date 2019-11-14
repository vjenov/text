package com.mypet.web.enums;

public enum SQL {
	CREATE_USER, DROP_USER, CREATE_DB, CREATE_CAT, DROP_CAT, TRUNCATE_USER, CREATE_ARTICLE;
	@Override
//	private String birth, sex, name, hairlength, haircolor, species, size, character, illhistory;
	public String toString() {
		String result = "";
		switch(this) {
		case CREATE_DB :
			result = "CREATE DATABASE MYPET";
			break;
		case CREATE_USER :
			result = "CREATE TABLE USER()";
			break;
		case DROP_USER :
			result = "DROP TABLE USER";
			break;
		case CREATE_CAT :
			result = "CREATE TABLE CAT("
					+ "BIRTH DATE,"
					+ "SEX VARCHAR(6),"
					+ "NAME VARCHAR(21),"
					+ "HAIRLENGTH VARCHAR(6),"
					+ "HAIRCOLOR VARCHAR(9),"
					+ "SPECIES VARCHAR(30),"
					+ "SIZE VARCHAR(9),"
					+ "STYLE VARCHAR(21),"
					+ "ILLHISTORY VARCHAR(60))";
			break;
		case DROP_CAT :
			result = "DROP TABLE CAT";
			break;
		case TRUNCATE_USER :
			result = "TRUNCATE TABLE USER";
			break;
		case CREATE_ARTICLE:
			result = "CREATE TABLE ARTICLE("
					+ "ARTICLESEQ int,"
					+ "UID VARCHAR(10),"
					+ "FOREIGN KEY(UID) REFERENCES USER(UID),"
					+ "COMMENTS VARCHAR(60),"
					+ "MSG VARCHAR(90),"
					+ "TITLE VARCHAR(60),"
					+ "CONTENT VARCHAR(120),"
					+ "CATEGORY VARCHAR(30),"
					+ "RATING int)";
		}
		return result;
	}
}
