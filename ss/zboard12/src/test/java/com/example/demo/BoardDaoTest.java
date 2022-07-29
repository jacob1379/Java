package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.BoardDao;
import com.example.demo.entity.Board;

@SpringBootTest
public class BoardDaoTest {
	@Autowired
	private BoardDao dao;
	
	//@Test
	public void initTest() {
		assertNotNull(dao);
	}
	//@Test
	public void writeTest() {
		Board board = Board.builder().title("aaa").content("bbb").nickname("spring").password("1234").build();
		assertEquals(1, dao.save(board));
	}
	//@Test
	public void readTest() {
		assertEquals(true, dao.findById(1).isPresent());
		assertEquals(true, dao.findById(111).isEmpty());
	}
	//@Test
	public void listTest() {
		// myBatis에서 여러개를 받아올 때(List로 받아올 때)
		// 결과가 없어도 null이 아닌 비어있는 List;
		// myBatis에서 한개를 받아올 때(Board로 받아올 때)
		// 글이 없으면 null
		assertNotEquals(0, dao.findAll().size());
	}
	
	//@Test
	public void updateTest() {
		Board board = Board.builder().title("xxx").content("yyy").bno(1).password("1234").build();
		assertEquals(1, dao.update(board));
	}
	
	@Transactional
	@Test
		public void deleteTest() {
			assertEquals(1, dao.deleteById(1));
		}
}
