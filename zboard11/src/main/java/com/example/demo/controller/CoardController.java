package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Coard;
import com.example.demo.service.CoardService;

@Controller
public class CoardController {
	
	@Autowired
	private CoardService service;
	
	@GetMapping(value="/coard/read", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> read(Integer bno) {
		Optional<Coard> coard = service.read(bno);
		if(coard.isEmpty())
			return ResponseEntity
					.status(HttpStatus.CONFLICT).body("검색 실패"); // string
		return ResponseEntity.status(HttpStatus.OK).body(coard.get()); // json
	}
}
