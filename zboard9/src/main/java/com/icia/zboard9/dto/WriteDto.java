package com.icia.zboard9.dto;

import javax.servlet.http.Part;

import com.icia.zboard9.entity.Board;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WriteDto {
	private String title;
	private String content;
	private String writer;
	private Part attachment;
	
	public Board toEntity() {
		return Board.builder().title(title).content(content).writer(writer).build();
	}
}	
