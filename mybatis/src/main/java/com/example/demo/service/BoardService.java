package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BoardDao;
import com.example.demo.dto.DeleteDto;
import com.example.demo.dto.ListDto;
import com.example.demo.dto.ReadDto;
import com.example.demo.dto.UpdateDto;
import com.example.demo.dto.WriteDto;
import com.example.demo.entity.Board;

@Service // Service Component - 스프링에게 해당 클래스가 Service Component이라고 알려 주는 것, Bean 객체로 생성하여  스프링(IoC 컨테이너)에 등록
		//Client의 요청에 대한 비즈니스 로직을 수행
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
