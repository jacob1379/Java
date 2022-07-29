package com.example.demo.dto;

import com.example.demo.entity.*;

import lombok.*;

@Getter
@Setter
@ToString
public class UpdateDto {
	private Integer bno;
	private String title;
	private String content;
	private String password;
	
	public Board toEntity() {
		return Board.builder().bno(bno).title(title).content(content).password(password).build();
	}

}
