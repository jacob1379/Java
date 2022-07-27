package com.icia.zboard9.entity;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Board {
	private Integer bno;
	private String title;
	private String content;
	private String writer;
	private String attachment;
	@Builder.Default
	private Date writeTime = new Date();
	@Builder.Default
	private Integer readCnt = 0;
	
	public void init(String UPLOAD_URL, String name, int bno) {
		this.attachment = UPLOAD_URL + name;
		this.bno = bno;
	}
	public void init(int bno) {
		this.bno = bno;
	}
}
