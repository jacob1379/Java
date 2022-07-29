package com.example.demo.dao;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.example.demo.dto.*;
import com.example.demo.entity.*;

@Mapper
public interface BoardDao {
	@Insert("insert into board(bno, title, content, nickname, password) values(board_seq.nextval, #{title},#{content},#{nickname}, #{password})")
	public int save(Board board);

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
