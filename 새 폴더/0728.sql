select emp.* from emp; --emp�κ��� ��� emp �÷� ��������

select e.* from emp e; -- ��Ī �ֱ�

select e.job from emp e;

select e.hiredate from emp e;
select hiredate from emp; -- ���̺� �ϳ��� �� �̷��� �ۼ�����.

select deptno from emp, dept; -- ���̺��� 2���̰� �̸��� ������ ����
select emp.deptno, dept.deptno from emp, dept; -- �̷��� ���������

select * from emp where 1=0; -- �ķ� ���� Ȯ���ϱ� ���ؼ�(������ �������� ����)
desc emp; -- ���̺� ���� Ȯ��, ���� ����

desc student;
select s.studno, s.name, s.birthday, s.tel from student s;

desc professor;
select p.profno, p.name, p.position, p.pay from professor p;

desc emp2;
select * from emp2 where 1=0;
select e.name, e.hobby, e.position, e.deptno from emp2 e;

-- ������ ����, ������ ��ųʸ�, ��Ÿ������ (user_�� �����ϴ� ���̺�)
-- �б�� ���������� ����� �Ұ���(DDL�� �����ϸ� ����Ŭ�� ����)

-- �ε����� Ÿ�� �˻��� ��������
-- Ŭ������ �ε��� : �ε��� ������ ������ ������ ��ġ(å�� ����) - �⺻Ű �ε���
-- �� Ŭ������ �ε��� : ���� ����ġ
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
-- ���� ���� : DB�� �������� �����ʹ� �Է��� �ź�
-- �⺻Ű�� ���� ��������(not null, unique) : ��ü ���Ἲ ��������

select * from user_sequences;
create sequence board_seq_bno;
insert into board values(board_seq_bno.nextval, 'aaa','bbb', 'spring', sysdate, 0);
commit;
select * from board;

----------------------- <    �ߺ�����   >

-- �ߺ��� ������ ������ �д´�? �о�� ���� �ߺ��� �����Ѵ�?
select distinct job from emp;

-- where���� ���ٸ� ���̺�� �˻� ����� ī��θ�Ƽ�� �׻� ����
-- ���� ������ ������ ����. �и��� �ٶ���
select distinct position from emp2;

select * from emp order by empno asc;

-- index�� Ÿ�鼭 �˻��ϱ⸦ ���ϴµ� ��Ÿ�� ��� hint�� ����������
-- where�� �ε����� �ɷ��ִ� �÷����� �ǹ̾��� ������ �Ǵ�

select * from emp where empno>0 order by empno asc;
select * from emp;

select * from emp where empno=7369 and rownum<=1;

----------------------- <    ����   >

-- order by�� ���ù����ϰ� ������
select * from emp order by empno asc;
-- ��Ʈ�� �̿��ؼ� �ε��� ������ ����ϰ� �ϸ� (������ �⺻���� ��������)
select /*+ index(pk_emp_emp)*/ * from emp; --�������� ����

select * from emp order by empno desc;
-- ��Ʈ�� �̿��ؼ� �ε��� �������� ����ϰ� �ϸ� (�ε����� ��������)
select /*+ index_desc(emp pk_emp) */ * from emp;

select * from emp order by sal desc;
select * from emp order by sal desc, empno asc;

select empno, ename, sal from emp order by 1 asc;

select * from emp order by hiredate asc;
select * from insa order by basicpay desc;
select * from professor order by name desc;

select * from emp where job='MANAGER'; -- �ȿ� ����ִ� ���� ��ҹ��� ������.

select empno, ename, job, deptno from emp where deptno =20
order by empno;

-- ����Ŭ�� Ÿ��: ���� -15, ���ڿ� - '15', ��¥ '2021-11-20','78/10/20'
select * from emp where hiredate >= '1982-01-01';

-- null�� ������ �ȵȴ�
select * from emp where comm is null;
select * from emp where comm is not null;
-- null���� nvl �Լ� : nvl(��1, ��2)
select sal, comm, nvl(sal+comm, sal) from emp;
select sal, comm, nvl(sal+comm, sal) salary from emp; -- �Լ� �̸��� ������(�ڹٰ� ���ޱ⶧��)

-- and(&&), or(||)
select * from emp where sal>=2000 and deptno=20;
select * from emp where sal>=2000 or deptno=20;
--�μ��� 20�̰� comm�� �޴� ���
--1981�⿡ �Ի��� 30�� �μ� ���
select * from emp where deptno=20 and comm is not null;
select * from emp where hiredate between '1981-01-01' and '1981-12-31' and deptno=30;

-- ������ and�� ����
select * from emp where sal>=2000 and sal<=3000;
select * from emp where sal between 2000 and 3000;
select * from emp where sal not between 2000 and 3000;

-- ������ or�� ����
select * from emp where deptno=10 or deptno=20 or deptno=30;
select * from emp where deptno in (10,20,30);
select * from emp where deptno not in (10,20,30);

select * from emp;

desc board;
create sequence board_seq;

select * from board;
select * from user_sequences;

desc board;