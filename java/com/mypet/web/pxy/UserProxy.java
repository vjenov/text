package com.mypet.web.pxy;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mypet.web.enums.SQL;
import com.mypet.web.usr.User;
import com.mypet.web.usr.UserMapper;
@Component("manager")
public class UserProxy extends Proxy{
	@Autowired UserMapper userMapper;
    public void setCalenders() {
   	 int[] maxDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        
        int iMinMonth = 1;
        int iMaxMonth = 12;
        
        int iRandomMonth = (int)(Math.random() * iMaxMonth - iMinMonth + 1) + iMinMonth;
        int iRandomDay = (int)(Math.random() * (maxDays[iRandomMonth-1] -2) + 1);
   }
   public String makeUsername(){
   	List<String> fname = Arrays.asList("김", "이", "박", "최", "정", "강", "조", "윤", "장", "임", "한", "오", "서", "신", "권", "황", "안",
   	        "송", "류", "전", "홍", "고", "문", "양", "손", "배", "조", "백", "허", "유", "남", "심", "노", "정", "하", "곽", "성", "차", "주",
   	        "우", "구", "신", "임", "나", "전", "민", "유", "진", "지", "엄", "채", "원", "천", "방", "공", "강", "현", "함", "변", "염", "양",
   	        "변", "여", "추", "노", "도", "소", "신", "석", "선", "설", "마", "길", "주", "연", "방", "위", "표", "명", "기", "반", "왕", "금",
   	        "옥", "육", "인", "맹", "제", "모", "장", "남", "탁", "국", "여", "진", "어", "은", "편", "구", "용");
   	    List<String> name = Arrays.asList("가", "강", "건", "경", "고", "관", "광", "구", "규", "근", "기", "길", "나", "남", "노", "누", "다",
   	        "단", "달", "담", "대", "덕", "도", "동", "두", "라", "래", "로", "루", "리", "마", "만", "명", "무", "문", "미", "민", "바", "박",
   	        "백", "범", "별", "병", "보", "빛", "사", "산", "상", "새", "서", "석", "선", "설", "섭", "성", "세", "소", "솔", "수", "숙", "순",
   	        "숭", "슬", "승", "시", "신", "아", "안", "애", "엄", "여", "연", "영", "예", "오", "옥", "완", "요", "용", "우", "원", "월", "위",
   	        "유", "윤", "율", "으", "은", "의", "이", "익", "인", "일", "잎", "자", "잔", "장", "재", "전", "정", "제", "조", "종", "주", "준",
   	        "중", "지", "진", "찬", "창", "채", "천", "철", "초", "춘", "충", "치", "탐", "태", "택", "판", "하", "한", "해", "혁", "현", "형",
   	        "혜", "호", "홍", "화", "환", "회", "효", "훈", "휘", "희", "운", "모", "배", "부", "림", "봉", "혼", "황", "량", "린", "을", "비",
   	        "솜", "공", "면", "탁", "온", "디", "항", "후", "려", "균", "묵", "송", "욱", "휴", "언", "령", "섬", "들", "견", "추", "걸", "삼",
   	        "열", "웅", "분", "변", "양", "출", "타", "흥", "겸", "곤", "번", "식", "란", "더", "손", "술", "훔", "반", "빈", "실", "직", "흠",
   	        "흔", "악", "람", "뜸", "권", "복", "심", "헌", "엽", "학", "개", "롱", "평", "늘", "늬", "랑", "얀", "향", "울", "련");
   	    Collections.shuffle(fname);
   	    Collections.shuffle(name);
   	    String fullname = fname.get(0) + name.get(0) + name.get(1);
   	    return fullname;
   }
   public String makeUserid() {
       List<String> idE =
       Arrays.asList("a","b","c","d","e","f","g"
       ,"h","i","j","k","l","n","m","o","p"
       ,"q","r","s","t","u","v","w","x","y","z",
       "A","B","C","D","E","F","G",
       "H","I","J","K","L","N","M","O","P"
       ,"Q","R","S","T","U","V","W","X","Y","Z",
       "0","1","2","3","4","5","6","7","8","9");
       Collections.shuffle(idE);
       return idE.get(0) + idE.get(1)+idE.get(2)+idE.get(3)+idE.get(4);    
   }
   public String makeUserphone() {
       return String.format("03%d-%4d-%4d",this.random(1,10),this.random(1,9999),this.random(1,9999));
   }
   public String setpetType() {
       List<String> pet = Arrays.asList("포메라이안","비숑프리제","스피츠","푸들","치와와","닥스훈트",
               "말티즈","시츄","요크셔테리어","특수견","시바견","웰시코기","프렌치불독",
               "비글","골든리트리버","레브라도리트리버","시베리안 허스키","보더콜리",
               "사모예드","버니즈마운틴","잉글리쉬불독","알레스카 말라뮤트");
       Collections.shuffle(pet);
       String pettype = pet.get(0);
       return pettype;
   }
   public User makeUser(){
		return new User(makeUserid(),"1", null, makeUsername(), makeUserphone(), null, null, setpetType());
	}
   @Transactional
   public void insertUsers() {
	   for(int i=0; i< 500; i++) {
		   userMapper.insertUser(makeUser());
	   }    		
   }
   public void truncateUsers() {
	   HashMap<String, String> map = new HashMap<>();
	   map.put("TRUNCATE_USER", SQL.TRUNCATE_USER.toString());
	   userMapper.truncateUser(map);
   }
  public List<?> makeAdd(){
	   List<String> city = Arrays.asList("서울특별시", "부산광역시", "인천광역시", "대구광역시", "대전광역시", "광주광역시", "울산광역시", "제주특별자치도", "세종특별자치시",
			   							"경기도", "충청북도", "충청남도", "강원도", "전라북도", "전라남도", "경상북도", "경상남도");
	   List<String> seoulState = Arrays.asList("강서구", "양천구", "구로구", "은평구", "마포구", "영등포구", "서대문구", "동작구", "관악구", "용산구", "종로구", "중구", "서초구", "강남구", "성동구", "성북구", "동대문구", "강북구", "도봉구", "노원구", "중랑구", "광진구", "송파구", "강동구");
	   List<String> busanState = Arrays.asList("기장군", "금정구", "북구", "동래구", "해운대구", "연제구", "수영구", "남구", "부산진구", "사상구", "동구", "서구", "중구", "영도구", "사하구", "강서구");
	   List<String> incheonState = Arrays.asList("중구", "동구", "미추홀구", "연수구", "남동구", "부평구", "계양구", "서구", "강화군", "옹진군");
	   List<String> daeguState = Arrays.asList("달성군", "달서구", "서구", "북구", "중구", "남구", "동구", "수성구");
	   List<String> daejeonState = Arrays.asList("대덕구", "유성구", "서구", "중구", "동구");
	   List<String> gwangjuState = Arrays.asList("광산구", "북구", "서구", "동구", "남구");
	   List<String> ulsanState = Arrays.asList("울주군", "북구", "중구", "남구", "동구");
	   List<String> jejuState = Arrays.asList("제주시", "서귀포시");
	   List<String> sejongState = Arrays.asList("조치원읍", "연기면", "연동면", "부강면", "금남면", "장군면", "연서면", "전의면", "전동면", "소정면", "한솔동", "새롬동", "도담동", "아름동", "종촌동", "고운동", "소담동", "보람동", "대평동");
	   List<String> geunggiState = Arrays.asList("수원시", "고양시", "용인시", "성남시", "부천시", "안산시", "화성시", "남양주시", "안양시", "평탱시", "시흥시", "파주시", "의정부시", "김포시", "광주시", "광명시", "군포시", "오산시", "하남시", "이천시", "양주시", "안성시", "구리시", "포천시", "의왕시", "여주시", "양평군", "동두천시", "가평군", "과천시", "연천군");
	return geunggiState;
   }
}
