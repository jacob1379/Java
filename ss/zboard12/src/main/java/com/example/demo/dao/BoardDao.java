package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.dto.ListDto;
import com.example.demo.dto.ReadDto;
import com.example.demo.entity.Board;

// JPA는 dao의 기본메소드 이름이 정해져 있다.
// DAO는 엔티티로 작업을 한다 -> JPA는 내보낼때는 DTO를 사용하더라....

@Mapper
public interface BoardDao {
	// bno, title, content,nickname,password를 저장
	@Insert("insert into board(bno, title, content, nickname, password) values(board_seq.nextval, #{title},#{content},#{nickname}, #{password}")
	public int save(Board board); // 동작의 성공 여부를 1,0으로
	
	// password를 빼고 읽어온다.
	@Select("select bno, title, content, nickname, writeTime, readCnt from board where bno=#{bno}")
	public Optional<ReadDto> findById(Integer bno);

	// bno, title, nickname, readCnt를 출력한다.
	@Select("select bno, title, nickname, readCnt from board")
	public List<ListDto> findAll();
	
	// bno로 찾아서 제목과 내용을 업데이트
	@Update("update board set title=#{title}, content=#{content} where bno=#{bno}")
	public int update(Board board);
	
	@Delete("delete from board where bno=#{bno}")
	public int deleteById(Integer bno);
	
	// 조회수 1증가
	@Update("update board set readCnt=readCnt+1 where bno=#{bno}")
	public int increaseReadCnt(Integer bno);
	
	// 비밀번호만 읽어내는 메소드
	@Select("select password from board where bno=#{bno}")
	public Optional<String> findPassword(Integer bno);
}
