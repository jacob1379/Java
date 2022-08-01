package com.example.demo.dto;

import java.time.*;

import com.fasterxml.jackson.annotation.*;

import lombok.*;

@Getter
public class ReadDto {
	private Integer bno;
	private String title;
	private String content;
	private String nickname;
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private LocalDateTime writeTime;
	private Integer readCnt;
}
