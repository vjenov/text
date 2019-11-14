package com.mypet.web.cat;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Lazy @Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class Cat{
	private String birth, sex, name, hairlength, haircolor, species, size, character, illhistory;
}
