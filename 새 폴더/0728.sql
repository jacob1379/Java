select emp.* from emp; --emp로부터 모든 emp 컬럼 가져오기

select e.* from emp e; -- 별칭 주기

select e.job from emp e;

select e.hiredate from emp e;
select hiredate from emp; -- 테이블 하나일 때 이렇게 작성가능.

select deptno from emp, dept; -- 테이블이 2개이고 이름이 같으면 오류
select emp.deptno, dept.deptno from emp, dept; -- 이렇게 적어줘야함

select * from emp where 1=0; -- 컴럼 명을 확인하기 위해서(조건을 거짓으로 설정)
desc emp; -- 테이블 구조 확인, 위와 동일

desc student;
select s.studno, s.name, s.birthday, s.tel from student s;

desc professor;
select p.profno, p.name, p.position, p.pay from professor p;

desc emp2;
select * from emp2 where 1=0;
select e.name, e.hobby, e.position, e.deptno from emp2 e;

-- 데이터 사전, 데이터 딕셔너리, 메타데이터 (user_로 시작하는 테이블)
-- 읽기는 가능하지만 쓰기는 불가능(DDL을 실행하면 오라클이 갱신)

-- 인덱스를 타면 검색이 빨라진다
-- 클러스터 인덱스 : 인덱스 순서와 데이터 순서가 일치(책의 목차) - 기본키 인덱스
-- 비 클래스터 인덱스 : 순서 불일치
select * from user_indexes where table_name='BOARD';

drop table board;

create table board(
    bno number(7) constraint board_pk_bno primary key,
    title varchar2(100 char),
    content clob,
    writer varchar2(10 char),
    writeTime date,
    readCnt number(3)
);

select * from user_constraints;
-- 제약 조건 : DB는 부적절한 데이터는 입력을 거부
-- 기본키에 대한 제약조건(not null, unique) : 객체 무결성 제약조건

select * from user_sequences;
create sequence board_seq_bno;
insert into board values(board_seq_bno.nextval, 'aaa','bbb', 'spring', sysdate, 0);
commit;
select * from board;

----------------------- <    중복제거   >

-- 중복을 제거한 다음에 읽는다? 읽어온 다음 중복을 제거한다?
select distinct job from emp;

-- where절이 없다면 테이블과 검색 결과의 카디널리티는 항상 같다
-- 직위 정보는 교수와 별개. 분리가 바람직
select distinct position from emp2;

select * from emp order by empno asc;

-- index를 타면서 검색하기를 원하는데 안타는 경우 hint로 가르쳐주자
-- where에 인덱스가 걸려있는 컬럼으로 의미없는 조건을 건다

select * from emp where empno>0 order by empno asc;
select * from emp;

select * from emp where empno=7369 and rownum<=1;

----------------------- <    정렬   >

-- order by는 무시무시하게 느리다
select * from emp order by empno asc;
-- 힌트를 이용해서 인덱스 순으로 출력하게 하면 (정렬은 기본으로 오름차순)
select /*+ index(pk_emp_emp)*/ * from emp; --정렬하지 않음

select * from emp order by empno desc;
-- 힌트를 이용해서 인덱스 역순으로 출력하게 하면 (인덱스는 내림차순)
select /*+ index_desc(emp pk_emp) */ * from emp;

select * from emp order by sal desc;
select * from emp order by sal desc, empno asc;

select empno, ename, sal from emp order by 1 asc;

select * from emp order by hiredate asc;
select * from insa order by basicpay desc;
select * from professor order by name desc;

select * from emp where job='MANAGER'; -- 안에 들어있는 값은 대소문자 구별함.

select empno, ename, job, deptno from emp where deptno =20
order by empno;

-- 오라클의 타입: 숫자 -15, 문자열 - '15', 날짜 '2021-11-20','78/10/20'
select * from emp where hiredate >= '1982-01-01';

-- null은 연산이 안된다
select * from emp where comm is null;
select * from emp where comm is not null;
-- null연산 nvl 함수 : nvl(값1, 값2)
select sal, comm, nvl(sal+comm, sal) from emp;
select sal, comm, nvl(sal+comm, sal) salary from emp; -- 함수 이름을 정해줌(자바가 못받기때문)

-- and(&&), or(||)
select * from emp where sal>=2000 and deptno=20;
select * from emp where sal>=2000 or deptno=20;
--부서가 20이고 comm을 받는 사원
--1981년에 입사한 30번 부서 사원
select * from emp where deptno=20 and comm is not null;
select * from emp where hiredate between '1981-01-01' and '1981-12-31' and deptno=30;

-- 조건이 and로 범위
select * from emp where sal>=2000 and sal<=3000;
select * from emp where sal between 2000 and 3000;
select * from emp where sal not between 2000 and 3000;

-- 조건이 or로 범위
select * from emp where deptno=10 or deptno=20 or deptno=30;
select * from emp where deptno in (10,20,30);
select * from emp where deptno not in (10,20,30);

select * from emp;

desc board;
create sequence board_seq;

select * from board;
select * from user_sequences;

desc board;