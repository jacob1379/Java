package com.example.demo.dto;

import com.example.demo.entity.Board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
