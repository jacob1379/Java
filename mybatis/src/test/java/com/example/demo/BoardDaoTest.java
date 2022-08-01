package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.transaction.annotation.*;

import com.example.demo.dao.*;
import com.example.demo.entity.*;

//@SpringBootTest // 설정 파일을 읽어와 부트 프로젝트를 실행해라.
public class BoardDaoTest {
	@Autowired
	private BoardDao dao;
	
	//@Test
	public void writeTest() { // MyBatis는 기본적으로 null입력이 막혀있다.
		Board board = Board.builder().title("aaa").content("bbb").nickname("spring").password("1234").build();
		//System.out.println(board);// bno가 안들어있는데 아래의
		assertEquals(1, dao.save(board));
		// 글을 추가할 때 글번호를 sequence를 사용한다.
		// 글을 작성하면 화면을 글읽기로 이동시킬 수도 있다 -> 글 번호 필요
		
		// Selectkey를 사용한 경우 리턴이 아니라 객체에 결과값이 저장된다.
		System.out.println("bno : " + board.getBno());
	}
	
	//@Test
	public void readTest() {
		assertEquals(true, dao.findById(1).isPresent());
		assertEquals(true, dao.findById(111).isEmpty());
	}
	
	//@Test
	public void listTest() {
		assertNotEquals(0, dao.findAll().size());
	}
	
	//@Transactional
	//@Test
	public void updateTest() {
		Board board = Board.builder().title("xxx").content("yyy").bno(1).password("1234").build();
		assertEquals(1, dao.update(board));
	}
	
	//@Transactional //처리 후 자동으로 rollback시킨다. (테스트 후 값이 넘어가지 않고 삭제됨)
	//@Test
	public void deleteTest() {
		assertEquals(1, dao.deleteById(1));
	}
}
