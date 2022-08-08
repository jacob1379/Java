-- 데이터베이스 메타데이터 : system catalog, data 사전, schema
-- 시스템 카탈로그는 속도 위주 세팅 -> 사람이 읽을 수 없는 형식으로 저장
-- 인덱스 : user_indexes 뷰에서 확인할 수 있다.
-- 오라클이 시스템 카탈로그에 대한 뷰를 제공한다 : user_
select * from user_indexes where table_name='EMP';

-- hint : 오라클 실행계획에 이렇게 해봐... 라고 건의
select /*+ index(emp pk_emp) */ * from emp;
select /*+ index_desc(emp pk_emp) */ empno from emp;
select * from emp where empno>0;

select max(empno)+1 from emp;
select /*+ index_desc(emp pk_emp) */ empno+1 from emp where rownum<=1;

select * from emp where empno=7369; and rownum=<1;

-- 인덱스가 없으면 테이블 전체(full)를 찾는다.
select * from emp where ename='SMITH' and rownum<=1;

-- 만약 인덱스가 없는 컬럼으로 자주 검색하는 경우 인덱스를 만들어주자
-- 인덱스를 만들면 그 컬럼 검색은 빨라져도 테이블 자체가 무거워진다.

-- 단일행 함수 : 오라클은 많은 함수를 제공한다. 이 함수들을 사용할까?
-- lower, upper
    select * from emp where lower(ename)='smith';
    -- ename에 인덱스를 지정한다고 해보자(create index ~ on)
    -- 인덱스가 걸려있는 컬럼을 변형하면 절대 인덱스를 못탄다.
    
-- substr(값, 시작위치, 자를크기)
    select substr(ename,1,2) from emp;
    --입사년도가 1981년이다.
    select * from emp where hiredate between '81-01-01' and '81-12-31';
    select * from emp where substr(hiredate,1,2)='81';
    
-- nvl

-- round 함수 : 자바가 반올림이 약해서 오라클에서 처리
    select empno, ename, round(sal*0.033,1) from emp;
    
-- 날짜 함수 : Date -> Calendar -> LocalDate, LocalDateTime
    select sysdate-hiredate from emp;
    
-- 단일행 함수 : 행마다 결과가 하나씩
-- 다중행 함수(그룹함수)  : 여러행에서.... (기본적으로는 테이블)에서 결과가 하나
    select lower(ename) from emp;
    select max(empno) from emp;
    
-- case :  오라클의 if문으로 계산 컬럼을 추가
    select sal, case when sal>=3000 then '고급여' else '저급여' end
    from emp;
    -- 급여가 3000이상이면 소득세율 5%, 미만이면 3%. 사원의 소득세를 출력하시오
    select emp.*,
    case when sal>=3000 then sal*0.05 else sal*0.03 end 소득세
    from emp;
    
-- rownum : 오라클이 select 결과행에 붙이는 행번호
select rownum, emp.* from emp where rownum=1;

select rownum, emp.* from emp where rownum=3;
-- 우선 smith의 행을 꺼내와서 조건 체크 : rownum=3 -> 조건 불만족 버린다.
-- 다음으로 allen의 행을 꺼낸다. allen 행의 rownum은 1이다. -> 조건 불만족 버린다.
select rownum, emp.* from emp where rownum<=3;

-- 급여 1등을 출력
-- 테이블엣허 5개를 꺼낸다은ㅁ 급여순으로 정렬 top-N 쿼리
-- from->where->select->order by : where 전에 order by가 실행되어야한다.
select * from emp where rownum<=5 order by sal desc;

-- 데이터를 정렬한 다음(첫번째 select) where 하자 (두번째 select)
select * from (select * from emp order by sal desc) where rownum<=5;

-- 뷰 : select 문에 이름붙인 것 -> 가상 테이블 
create view my_view as select * from emp order by sal desc;
-- inline view : select문이 from 다음에 와서 한번만 사용하는 이름없는 뷰
--                임시 테이블

select * from (select * from emp order by sal desc) where rownum<=5;
select * from (select * from emp order by hiredate desc) where rownum=1;
select ename from (select * from emp where deptono=30 order by sal asc) where rownum<=2;
select * from (select * from student where deptno2 is not null order by birthday asc) where rownum<=2;
select name from (select * from professor where position='조교수' order by pay desc) where rownum<=2;
select * from (select gname, point from gogak order by point desc) where rownum=1;
