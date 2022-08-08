-- �����ͺ��̽� ��Ÿ������ : system catalog, data ����, schema
-- �ý��� īŻ�α״� �ӵ� ���� ���� -> ����� ���� �� ���� �������� ����
-- �ε��� : user_indexes �信�� Ȯ���� �� �ִ�.
-- ����Ŭ�� �ý��� īŻ�α׿� ���� �並 �����Ѵ� : user_
select * from user_indexes where table_name='EMP';

-- hint : ����Ŭ �����ȹ�� �̷��� �غ�... ��� ����
select /*+ index(emp pk_emp) */ * from emp;
select /*+ index_desc(emp pk_emp) */ empno from emp;
select * from emp where empno>0;

select max(empno)+1 from emp;
select /*+ index_desc(emp pk_emp) */ empno+1 from emp where rownum<=1;

select * from emp where empno=7369; and rownum=<1;

-- �ε����� ������ ���̺� ��ü(full)�� ã�´�.
select * from emp where ename='SMITH' and rownum<=1;

-- ���� �ε����� ���� �÷����� ���� �˻��ϴ� ��� �ε����� ���������
-- �ε����� ����� �� �÷� �˻��� �������� ���̺� ��ü�� ���ſ�����.

-- ������ �Լ� : ����Ŭ�� ���� �Լ��� �����Ѵ�. �� �Լ����� ����ұ�?
-- lower, upper
    select * from emp where lower(ename)='smith';
    -- ename�� �ε����� �����Ѵٰ� �غ���(create index ~ on)
    -- �ε����� �ɷ��ִ� �÷��� �����ϸ� ���� �ε����� ��ź��.
    
-- substr(��, ������ġ, �ڸ�ũ��)
    select substr(ename,1,2) from emp;
    --�Ի�⵵�� 1981���̴�.
    select * from emp where hiredate between '81-01-01' and '81-12-31';
    select * from emp where substr(hiredate,1,2)='81';
    
-- nvl

-- round �Լ� : �ڹٰ� �ݿø��� ���ؼ� ����Ŭ���� ó��
    select empno, ename, round(sal*0.033,1) from emp;
    
-- ��¥ �Լ� : Date -> Calendar -> LocalDate, LocalDateTime
    select sysdate-hiredate from emp;
    
-- ������ �Լ� : �ึ�� ����� �ϳ���
-- ������ �Լ�(�׷��Լ�)  : �����࿡��.... (�⺻�����δ� ���̺�)���� ����� �ϳ�
    select lower(ename) from emp;
    select max(empno) from emp;
    
-- case :  ����Ŭ�� if������ ��� �÷��� �߰�
    select sal, case when sal>=3000 then '��޿�' else '���޿�' end
    from emp;
    -- �޿��� 3000�̻��̸� �ҵ漼�� 5%, �̸��̸� 3%. ����� �ҵ漼�� ����Ͻÿ�
    select emp.*,
    case when sal>=3000 then sal*0.05 else sal*0.03 end �ҵ漼
    from emp;
    
-- rownum : ����Ŭ�� select ����࿡ ���̴� ���ȣ
select rownum, emp.* from emp where rownum=1;

select rownum, emp.* from emp where rownum=3;
-- �켱 smith�� ���� �����ͼ� ���� üũ : rownum=3 -> ���� �Ҹ��� ������.
-- �������� allen�� ���� ������. allen ���� rownum�� 1�̴�. -> ���� �Ҹ��� ������.
select rownum, emp.* from emp where rownum<=3;

-- �޿� 1���� ���
-- ���̺��� 5���� ���������� �޿������� ���� top-N ����
-- from->where->select->order by : where ���� order by�� ����Ǿ���Ѵ�.
select * from emp where rownum<=5 order by sal desc;

-- �����͸� ������ ����(ù��° select) where ���� (�ι�° select)
select * from (select * from emp order by sal desc) where rownum<=5;

-- �� : select ���� �̸����� �� -> ���� ���̺� 
create view my_view as select * from emp order by sal desc;
-- inline view : select���� from ������ �ͼ� �ѹ��� ����ϴ� �̸����� ��
--                �ӽ� ���̺�

select * from (select * from emp order by sal desc) where rownum<=5;
select * from (select * from emp order by hiredate desc) where rownum=1;
select ename from (select * from emp where deptono=30 order by sal asc) where rownum<=2;
select * from (select * from student where deptno2 is not null order by birthday asc) where rownum<=2;
select name from (select * from professor where position='������' order by pay desc) where rownum<=2;
select * from (select gname, point from gogak order by point desc) where rownum=1;
