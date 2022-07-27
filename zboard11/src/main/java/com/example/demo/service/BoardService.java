package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import com.example.demo.dao.*;
import com.example.demo.entity.*;

// 서비스는 작업을 위해 dao를 주입받야야 한다.
// 한편 서비스의 객체도 ApplicationContext에 등록되어야 한다.

@Service // 자기 자신의 객체도 생성 , controller -> service -> DAO -> DB
public class BoardService {
	@Autowired
	private BoardDao dao;
	
	public Optional<Board> read(Integer bno) {
		return dao.read(bno);
	}
}
