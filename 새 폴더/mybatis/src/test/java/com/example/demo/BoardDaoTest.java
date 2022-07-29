package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.transaction.annotation.*;

import com.example.demo.dao.*;
import com.example.demo.entity.*;

@SpringBootTest
public class BoardDaoTest {
	@Autowired
	private BoardDao dao;
	
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
	
	@Test
	public void listTest() {
		assertNotEquals(0, dao.findAll().size());
	}
	
	@Transactional
	//@Test
	public void updateTest() {
		Board board = Board.builder().title("xxx").content("yyy").bno(1).password("1234").build();
		assertEquals(1, dao.update(board));
	}
	
	@Transactional
	//@Test
	public void deleteTest() {
		assertEquals(1, dao.deleteById(1));
	}
}
