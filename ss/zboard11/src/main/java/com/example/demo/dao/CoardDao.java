package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.Coard;

@Mapper
public interface CoardDao {
	//  SQL 표준 : insert, delete, update의 결과는 변경된 행의 수
	@Insert("")
	public int save(Coard coard);
	
	@Select("")
	public List<Coard> list();
	
	@Select("select * from board where bno=#{bno}")
	public Optional<Coard> read(Integer bno);
}
