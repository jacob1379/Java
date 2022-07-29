package com.example.demo.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.service.*;

@Controller
public class BoardController {
	@Autowired
	private BoardService service;
	
	@GetMapping(value="/board/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ListDto>> list() {
		return ResponseEntity.ok(service.list());
	}
	
	@PostMapping(value="/board/new", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Board> write(@ModelAttribute WriteDto dto) {
		System.out.println(dto);
		return ResponseEntity.ok(service.write(dto));
	}
	
	@GetMapping(value="/board/read", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> read(@RequestParam Integer bno) {
		Optional<ReadDto> result = service.read(bno);
		if(result.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("글을 찾을 수 없습니다"));
		return ResponseEntity.ok(result.get());
	}
	
	@PutMapping(value="/board/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageResponse> update(@ModelAttribute UpdateDto dto) {
		boolean result = service.update(dto);
		if(!result)
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse("글을 변경하지 못했습니다"));
		return ResponseEntity.ok(new MessageResponse((dto.getBno() + "번 글을 변경했습니다")));
	}
	
	@DeleteMapping(value="/board/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageResponse> delete(@ModelAttribute DeleteDto dto) {
		boolean result = service.delete(dto);
		if(!result)
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse("글을 삭제하지 못했습니다"));
		return ResponseEntity.ok(new MessageResponse((dto.getBno() + "번 글을 삭제했습니다")));
	}
}
