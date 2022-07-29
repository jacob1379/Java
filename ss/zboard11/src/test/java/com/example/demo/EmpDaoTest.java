package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dao.EmpDao;

@SpringBootTest
public class EmpDaoTest {
	@Autowired
	EmpDao dao;
	
	//@Test
	public void initTest() {
		assertNotNull(dao);
	}
	
	//@Test
	public void getJobTest() {
		assertEquals(5, dao.getJbo().size());
	}
	
	@Test
	public void getEmpInfoTest() {
		dao.getEmpInfo1().forEach(System.out::println);
		dao.getEmpInfo2().forEach(System.out::println);
	}
}
