package com.example.demo.controller;


import java.util.Optional;


import org.springframework.beans.factory.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.*;
import com.example.demo.service.*;

@Controller
//해당 어노테이션이 적용된 클래스는 "Controller"임을 나타나고, bean으로 등록되며
//해당 클래스가 Controller로 사용됨을 Spring Framework에 알립니다.
public class BoardController {
	@Autowired
	private BoardService service;
	
	// /board/read. Integer bno-> 0 -> service -> json + 200/409
	// ResponseEntity는 출력데이터 + 상태코드 클래스
	@GetMapping(value="/board/read", produces=MediaType.APPLICATION_JSON_VALUE)
	// get을 요청하는 어노테이션, 데이터를 가져올 때 사용한다.
	// produces는 서버가 클라이언트에게 반환하는 데이터 타입을 명시한다.
	// MediaType 받고 싶은 데이터를 강제함
	public ResponseEntity<Board> read
		(@RequestParam(defaultValue="0") Integer bno) {
		// 값이 없을 경우 파라미터에 매핑될 기본 값을 세팅
		Optional<Board> board = service.read(bno); // 여기서 service호출해서 입력값이 넘어간다.
		return ResponseEntity.ok(board.get());
		// 상태코드 + 출력 메시지
	}
}
