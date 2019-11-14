package com.mypet.web.usr;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Lazy @Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class User{
	private String uid,pwd,ssn,uname,phone,address,email,petType;
}
