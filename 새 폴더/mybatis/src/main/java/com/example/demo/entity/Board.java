package com.example.demo.entity;

import java.time.*;

import lombok.*;

@Getter
@Builder
public class Board {
	private Integer bno;
	private String title;
	private String content;
	private String nickname;
	private String password;
	private LocalDateTime writeTime;
	private Integer readCnt;
}
