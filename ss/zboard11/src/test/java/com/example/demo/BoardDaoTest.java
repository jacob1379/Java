package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.*;
import com.example.demo.entity.*;

// 설정 파일을 읽어와 부트 프로젝트를 실행해라.
@SpringBootTest //아까 작성한 application.properties 불러옴
public class BoardDaoTest {
	// 스프링에게 BoardDao 주세요... 라고 요청
	@Autowired  
	BoardDao dao;
	
	//@Test
	public void initTest() {
		assertNotNull(dao);
	}
	
	//@Test
	public void readTest() {
		Board board = dao.read(1).get();
		System.out.println(board);
	}
	// @Transactional을 사용하면 insert후 자동으로 rollback시킨다. 테스트 후 삭제
	//@Transactional
	@Test
	public void writeTest() { // MyBatis는 기본적으로 null입력이 막혀있다.
		Board board = Board.builder().title("ccc").content("ccc").writer("ccc").build();
		assertEquals(1, dao.save(board));
	}
}
