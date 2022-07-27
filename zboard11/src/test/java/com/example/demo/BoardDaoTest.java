package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

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
	
	@Test
	public void readTest() {
		Board board = dao.read(1).get();
		System.out.println(board);
	}
}
