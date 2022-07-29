package com.example.demo.service;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.example.demo.dao.*;
import com.example.demo.dto.*;
import com.example.demo.entity.*;

@Service
public class BoardService {
	@Autowired
	private BoardDao dao;
	
	public Board write(WriteDto dto) {
		Board board = dto.toEntity();
		dao.save(board);
		return board;
	}
	
	public boolean update(UpdateDto dto) {
		System.out.println(dto);
		Optional<String> result = dao.findPassword(dto.getBno());
		if(result.isEmpty() || result.get().equals(dto.getPassword())==false)
			return false;
		dao.update(dto.toEntity());
		return true;
	}
	
	public List<ListDto> list() {
		return dao.findAll();
	}
	
	public Optional<ReadDto> read(Integer bno) {
		dao.increaseReadCnt(bno);
		return dao.findById(bno);
	}
	
	public boolean delete(DeleteDto dto) {
		Optional<String> result = dao.findPassword(dto.getBno());
		if(result.isEmpty() || result.get().equals(dto.getPassword())==false)
			return false;
		dao.deleteById(dto.getBno());
		return true;
	}
}
