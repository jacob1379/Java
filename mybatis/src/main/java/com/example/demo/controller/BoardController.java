package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.DeleteDto;
import com.example.demo.dto.ListDto;
import com.example.demo.dto.MessageResponse;
import com.example.demo.dto.ReadDto;
import com.example.demo.dto.UpdateDto;
import com.example.demo.dto.WriteDto;
import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;

@Controller // Client의 요청을 직접적으로 받는 Component
			//해당 어노테이션이 적용된 클래스는 "Controller"임을 나타나고, bean으로 등록되며
			//해당 클래스가 Controller로 사용됨을 Spring Framework에 알립니다.
public class BoardController {
	@Autowired
	private BoardService service;
	
	@GetMapping(value="/board/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ListDto>> list() { //ResponseEntity는 출력데이터 + 상태코드 클래스
		return ResponseEntity.ok(service.list());
	}
		// get을 요청하고 매핑하는 어노테이션, 데이터를 가져올 때 사용한다.
		// produces는 서버가 클라이언트에게 반환하는 데이터 타입을 명시한다.
		// MediaType 받고 싶은 데이터를 강제함
	
	@PostMapping(value="/board/new", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Board> write(@ModelAttribute WriteDto dto) {
		System.out.println(dto);
		return ResponseEntity.ok(service.write(dto));
	} // HttpEntity를 상속(확장)하여 headers와 body를 포함하고, 추가로 HttpStatus 코드까지 함께 추가할 수 있는 클래스로
	  // rest컨트롤러 혹은 일반 컨트롤러에서 응답하는 객체로서 사용된다.
	  // HttpEntity는 header와 body로 구성되어 http 요청 혹은 응답 Entity로서 사용되는 클래스이다.
	  // 즉, Http 요청에 응답 Entity로 사용될 수 있는 클래스
	
	@GetMapping(value="/board/read", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> read(@RequestParam(defaultValue="0") Integer bno) { // 값이 없을 경우 파라미터에 매핑될 기본 값을 세팅
		Optional<ReadDto> result = service.read(bno);
		if(result.isEmpty())	//httpstatus.not_found - 404상태코드를 의미
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("글을 찾을 수 없습니다"));
		return ResponseEntity.ok(result.get());
	}
	
	@PutMapping(value="/board/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageResponse> update(@ModelAttribute UpdateDto dto) {
		boolean result = service.update(dto);
		if(!result)		//httpstatus.conflict - 409상태코드를 의미
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
