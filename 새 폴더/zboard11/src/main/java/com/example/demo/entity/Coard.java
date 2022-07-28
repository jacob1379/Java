package com.example.demo.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Coard {
	private Integer bno;
	private String title;
	private String content;
	private String writer;
	// 자바는 날짜 클래스에 문제가 많았다.
	// 그래서 자바8에서 JodaTime 라이브러리를 도입 : LocalDate, LocalDateTime
	// 뭐가 개선됐냐? 1. 날짜 계산, 2. 타임존
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime writeTime;
	private Integer readCnt;
}
