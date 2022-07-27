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
public class BoardController {
	@Autowired
	private BoardService service;
	
	// /board/read. Integer bno-> 0 -> service -> json + 200/409
	// ResponseEntity는 출력데이터 + 상태코드 클래스
	@GetMapping(value="/board/read", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Board> read
		(@RequestParam(defaultValue="0") Integer bno) {
		Optional<Board> board = service.read(bno);
		return ResponseEntity.ok(board.get());
	}
}
