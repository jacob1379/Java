package com.example.demo.dto;

import com.example.demo.entity.*;

import lombok.*;

@Getter
@Setter
@ToString
public class WriteDto {
	private String title;
	private String content;
	private String nickname;
	
	public Board toEntity() {
		return Board.builder().title(title).content(content).nickname(nickname).build();
	}

}
