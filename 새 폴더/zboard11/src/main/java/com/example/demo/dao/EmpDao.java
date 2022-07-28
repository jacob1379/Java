package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.Emp;

/*
스프링의 주요 역할
1. 객체 생성 및 관리를 담당(프로그래머는 new 금지 -> 제어 역전(IOC))
	- 객체 생성 어노테이션
		@Component -> @Controller, @Service, @Repository
		- @Autowired를 만나면 객체를 주입한다(DI)
		주입 방식은 byType
2. AOP
	- 내 업무 -> 관심(concern)
	- 횡단 관심 : 반복되는 부가 업무
	
프레임워크를 컨테이너 라고도 한다.
스프링 프레임워크는 스프링 컨테이너, DI*AOP 컨테이너, IoC*AOP 컨테이너
스프링 컨테이너의 중심 객체는 ApplicationContext이다.

*/

@Mapper
public interface EmpDao {
	@Select("select distinct job from emp")
	public List<String> getJbo();
	
	@Select("select empno, ename, job from emp")
	public List<Map<String, Object>> getEmpInfo1(); // 클래스 없어서 map사용
	
	@Select("select empno, ename, job from EMP")
	public List<Emp> getEmpInfo2();
}
