package com.example.demo.dao;

import java.util.List;

import java.util.Optional;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.example.demo.dto.ListDto;
import com.example.demo.dto.ReadDto;
import com.example.demo.entity.Board;
//객체를 관리해준다 -> 의존성 주입(Dependency Injection DI) -통일성을 위해
//application context로 객체가 저장된다.(context는 스프링이 관리하는 빈들이 담겨있는 컨테이너)

@Mapper //객체를 생성해 스프링에 등록하는 MyBatis어노테이션
public interface BoardDao {
	// SelectKey : insert하기 전에 board_seq.nextval을 먼저 실행해서 board에 저장한다. / 자동생성키
	// 			   (DB에 명령을 한번만 보내며, 우선 입력한 값의 결과값을 다음 쿼리로 바로 return 시켜주는 것이다.)
	@SelectKey(statement = "select board_seq.nextval from dual", keyProperty="bno", resultType=Integer.class, before = true) // 작성 후 read or list로 화면이동할 경우 bno가 필요함, 그래서 bno를 넘겨주기 위한 어노테이션
	@Insert("insert into board(bno, title, content, nickname, password) values(#{bno}, #{title},#{content},#{nickname}, #{password})")
	public int save(Board board); // 추가된 행의 갯수

	@Select("select bno, title, content, nickname, writeTime, readCnt from board where bno=#{bno}")
	public Optional<ReadDto> findById(Integer bno);
	
	@Select("select bno, title, nickname, writeTime, readCnt from board")
	public List<ListDto> findAll();
	
	@Update("update board set title=#{title}, content=#{content} where bno=#{bno}")
	public int update(Board board);
	
	@Delete("delete from board where bno=#{bno}")
	public int deleteById(Integer bno);
	
	@Update("update board set readCnt=readCnt+1 where bno=#{bno}")
	public int increaseReadCnt(Integer bno);
	
	@Select("select password from board where bno=#{bno}")
	public Optional<String> findPassword(Integer bno);
	
}
