package com.example.demo.dao;

import java.util.*;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.*;

// DAO : Data Access Object. SQL 작업하는 클래스들
//		요즘은 Repository라고들 한다.
// MyBatis에서 DAO를 만드는 방법은 3가지
// 스프링은 객체를 관리한다. - MyBatis한테 이 인터페이스의 객체를 만들어 스프링에 등록하라고 지정하자
// 객체를 관리해준다 -> 의존성 주입(Dependency Injection DI) -통일성을 위해

@Mapper // 객체를 생성해 스프링에 등록하는 MyBatis어노테이션
public interface BoardDao {
	@Select("select * from board where bno=#{bno}")
	public Optional<Board> read(Integer bno);
	
	// 글 전체 목록을 읽어오는 list메소드를 추가하시오.
	
	@Select("select * from board")
	public List<Board> list();
	// pageno, pagesize, totalcount, list<Board>
}
