# 오라클 데이터베이스 11g 프래그래밍 기초

## 데이터베이스 기본 개념

### 데이터베이스 개요

* 자료(Data)
  * 단순한 사실이나 값
* 정보(Information)
  * 의사결정에 도움을 줄 수 있는 유용한 형태
  * 자료를 가공처리 해서 얻을 수 있는 결과
* 데이터베이스-데이터를 모아놓은 곳
  * 어느 한 조직체의 여러 응용 시스템들이 **공동**으로 사용할 수 있도록 **통합**하여 **저장**한 **운영데이터**의 집합

### 데이터베이스의 정의

* 통합된 데이터
  * 한 곳에 있어야 함
  * 중복 최소화
* 저장 데이터
  * 접근 가능한 저장 매체에 저장된 데이터
* 운영 데이터
  * 고유 기능을 수행하기 위해 유지되어야 할 데이터
* 공용 데이터
  * 한 조직의 여러 응용 시스템들이 공동으로 소유,유지, 이용하는 데이터

### 데이터베이스의 특징

> 어느 한 조직의 응용 시스템들이 공유해서 사용하는 통합 저장된 운영 데이터

* 실시간 접근 가능
  * 비정형적인 질의에 대하여 실시간 처리로 응답
* 계속적인 변화
  * 새로운 데이터 삽입, 삭제, 갱신으로 항상 변화고 그 속에서 현재의 정확한 데이터 유지 가능
* 동시 공유 가능
  * 여러 사용자가 동시에 자기가 원하는 데이터 접근
* 내용에 의한 참조
  * 레코드 위치나 주소가 아닌 사용자가 요구하는 데이터 내용, 데이터가 가지고 있는 값에 따라 참조
  * 모든 레코드들은 물리적 위치와 상관없이 하나의 논리적 단위로 취급하고 접근

### 데이터베이스 구성 요소

* 개체 (entity)-하나의 항목

  * 개체 타입(유형)
  * 개체 집합: 개체 어커런스(인스턴스)

* 속성(attribute)-칼럼

  > 레코드-열(row)
  >
  > 필드-컬럼(column)
  >
  > 개체-테이블

### DBMS

* DBMS(Database Management System)-하나의 소프트웨어(oracle, MSQL,MySQL)
  * 사용자에게 데이터베이스 생성하고 유지할 수 있게 하는 프로그램 집합
  * DBMS는 데이터베이스 언어를 가지고 있으며, 이를 통해 데이터 삽입/삭제/수정
* DBMS기능
  * 데이터 무결성 유지
    * DB의 데이터는 실세계를 적용하는 규칙 만족해야 함
    * 서로 다른 부분이 있는 두 개의 데이터가 서로 다른 모순되지 않아야 함. 데이터 일관성 유지
* DBMS의 3가지 특성
  * 데이터의 논리적 독립성
    * 한 속성에 가해진 변경이 동일한 테이블에서 다른 속성에 영향 주지x
    * 응용 프로그램에 영향 주지 않고 데이터 구조 변경할 수 있게 하는 것
  * 참조 무결성과 데이터 무결성
    * 응용 프로그램 개발 시 무결성 제약 조건 신경 쓰지 않아도 됨
  * 비정규 질의
    * 사용자는 작업을 실행하는 방법을 명시하지 않고도 데이터베이스에 어떤 데이터를 조회할 것인지 명령 가능

### 관계형 데이터 모델의 구성 요소

* 관계형 데이터 모델의 용어
  * 테이블(table=relation)-entity
    * SQL에서 릴레이션 보다 테이블이란 용어 사용
  * 열(column)
  * 행(row)-튜플/레코드
    * 행은 순서 정해져 있지 않음
* 테이블들을 모아서 전체 스키마 구성

### SQL

* SQL이란?
  * SQL(Structured Query Language) 관계형 데이터베이스 언어
  * 데이터 삽입, 삭제, 갱신, 질의, 보호(보안) 명령문 구성



## 개발환경 구성하기

* oracle 11g, SQL developer설치

### oracle 11g설치

* oracle 11g다운로드

  > https://www.oracle.com/database/technologies/oracle-database-software-downloads.html#11g

  * file1, file2를 다운로드 받고, 하나의 database로 합치기

* `setup.exe`실행

  * 시스템 클래스
    * 데스크톱 클래스- 혼자 데이터베이스 사용
    * 서버클래스- 네트워크상에 있는 기타 여러 pc들과 네트워크를 통해 데이터베이스 같이 사용
  * Grid 설치 옵션
    * 단일 인스턴스 데이터베이스 설치
  * 설치유형
    * 표준설치

* 데이터베이스 설치-> 현재 컴퓨터를 데이터서버로 이용

* 설치완료 후

  * 비밀번호 관리
    * HR계정 풀기-비밀번호 1234

* 데이터베이스 컨트롤 url: 

* 설치확인

  * cmd창

    ```powershell
    sqlplus
    사용자명 입력:HR
    비밀번호:1234
    ```

  * ctrl+r->services.msc(현재 윈도우상에서 제공되고 있는 서비스)

    * oracle 관련 서비스 실행되고 있으면 제대로 설치

### DBeaver에서 oracle사용

1. connection setting
   * host:localhost
   * databse:ORCL

2. oracle JDBC다운로드
   * open download page를 눌러서 jar파일 다운받기
3. jar 파일 추가-ojdbc6.jar



## insert, update, delete, commit, rollback문

### DML(Data Manipulation Language)

데이터 조작어

* 종류: select문, delete문, insert문, update문

* 형식

  * select문

    ```sql
    select 컴럼1, 컬럼2, .. 
    from 테이블1, 테이블2...
    where 조건들;
    ```

  * insert문

    ```sql
    insert into 테이블명(컬럼1, 칼럼2,...) values(값1, 값2,...)
    ```

  * update문

    ```sql
    update 테이블명
    	set 칼럼1 = 값1,
    		칼럼2 = 값2,
    where 조건...;
    ```

  * delete문

    ```sql
    delete (from) 테이블명
    where 조건; //테이블의 레코드/로우 삭제
    ```

  * commit/rollback

    ```sql
    commit;
    rollback;//commit한 시점으로 돌아감
    ```

    * ex)

      ```sql
      CREATE TABLE sample(
      	deptno number(20),
      	deptName varchar2(15),
      	deploc varchar2(15),
      	depManager varchar2(10)
      );
      INSERT INTO sample values(10,'기획실','서울','홍길동');
      INSERT INTO sample values(20,'전산실','부산','김말동');
      INSERT INTO sample values(30,'영업부','광주',null);
      SELECT * FROM sample;
      COMMIT;
      DELETE FROM sample;
      SELECT * FROM sample;
      ROLLBACK;
      SELECT * FROM sample;
      ```

      

## 시퀀스 사용하기

### 시퀀스

* 연속적인 번호 만들어 주는 기능

### 구문형식

* sequence 생성

  ```sql
  create sequence 시퀀스 이름
  	increment by n --n:증가값 설정/기본값:1 
  	start with n --시작값 설정/기본값:1
  	maxvalue n | nomaxvalue --시퀀스 최대값 설정
  	minvalue n | nominvalue --시퀀스 최소값 설정/cycle옵션일 경우 시작값
  	cycle | nocycle --시퀀스 순환 사용할지 결정
  	cache n | nocache --시퀀스 속도 개선하기 위해 캐싱여부 지정
  ```

  * data는 보조기억 장치에 저장됨->data를 가져오는데 오래 걸림

    ->자주 사용되는 명령어는 cache메모리 영역에 두어 처리

* sequence삭제

  ```sql
  drop sequence 시퀀스명;
  ```

* sequence 이름 변경

  ```sql
  rename [시퀀스명] to [변경할 시퀀스명];
  ```

### 실습

* **시퀀스 생성**: 제품번호 생성하는 시퀀스 만들기

  ```sql
  /*시퀀스 생성*/
  CREATE SEQUENCE seq_serial_no 
  INCREMENT BY 1
  START WITH 100
  MAXVALUE 110
  MINVALUE 99
  CYCLE 
  cache 2;
  
  /*제품 테이블 생성*/
  CREATE TABLE good(
  	good_no number(3),
  	good_name varchar2(10)
  );
  
  INSERT INTO good values(seq_serial_no.nextval,'제품1');--nextval:다음값
  INSERT INTO good values(seq_serial_no.currval,'제품2');--currval:현재값
  SELECT seq_serial_no.currval FROM dual;
  ```

* cache 테스트

  ```powershell
  sqlplus hr/1234;
  
  SQL> CREATE TABLE good2(
  	 good_no number(3),
  	 good_name varchar2(10)
       );
  
  SQL> CREATE SEQUENCE seq_serial_no2 
  		INCREMENT BY 1
  		START WITH 100
  		MAXVALUE 105
  		cache 2;
  
  SQL> INSERT INTO good2 values(seq_serial_no2.nextval, '제품1');
  SQL> COMMIT;
  SQL> conn sys/1234 as sysdba;
  SQL> shutdown abort //데이터베이스 종료
  SQL> startup //데이터베이스 시작
  SQL> select * from good2;
  SQL> INSERT INTO good2 values(seq_serial_no2.nextval, '제품2');
  ```

  * good2의 테이블에서 '제품2'는 good_no이 102가 됨

    * cache값을 2로 설정함

    * 처음 insert를 하면, good_no은 100

      ->cache를 2로 잡았기 때문에 sequence의 숫자가 101로 cache에 저장

    * shut down을 하게 되면 cache값이 사라지게 되고, 새로 시작했을 때 sequence가 102로 잡히게 됨



## 계층형 쿼리

### 계층형 쿼리

> oracle에서만 제공(8i부터 제공)

* 계층 구조

  * 부모노드-자식노드-리프노드
  * 맨 위의 노드: root 노드

* 계층 구조를 table로 저장

  BOM 테이블

  | item_id | parent_id | item_name | 수량 |
  | ------- | --------- | --------- | ---- |
  | 100     | null      | 스마트폰  |      |
  | 101     | 100       | 보드      |      |
  | 102     | 100       | battery   |      |
  | 104     | 101       | cpu       |      |

  ```sql
  CREATE TABLE bom_sphone(
  	item_id number(3) NOT NULL,
  	parent_id number(3),
  	item_name varchar2(20) NOT NULL,
  	PRIMARY key(item_id)
  );
  
  INSERT INTO BOM_SPHONE values(100,NULL,'스마트폰');
  INSERT INTO BOM_SPHONE values(101,100,'메인pcb');
  INSERT INTO BOM_SPHONE values(102,100,'배터리');
  INSERT INTO BOM_SPHONE values(103,101,'cpu');
  INSERT INTO BOM_SPHONE values(104,101,'메모리');
  INSERT INTO BOM_SPHONE values(105,101,'블루투스');
  COMMIT;
  
  SELECT * FROM bom_sphone;
  ```

* 계층형 구조로 출력

  ```sql
  SELECT s1.item_id,s1.item_name, s2.item_name parent_name
  FROM bom_sphone s1, bom_sphone s2
  WHERE s1.parent_id=s2.item_id(+)
  ORDER BY s1.item_id;
  --계층적인 정보를 볼 수 없음
  
  --계층형 쿼리 사용
  /* start with, connect by절을 이용한 계층형 쿼리를 할 수 있다*/
  SELECT LPAD(' ',2*(LEVEL-1)) || item_name itemnames
  FROM bom_sphone 
  START WITH parent_id IS null--최상위 루트 지정
  CONNECT BY PRIOR item_id=parent_id;
  ```

  * start with, connect by절을 이용해 계층형 쿼리를 할 수 있다
    * start with: 최상위 루트 지정
    * connect by: 연결, prior은 부모의 행을 찾아 주는 역할
      * 현재의 id앞에 prior을 붙여줘야 함

* 예시

  ```sql
  select level, lpad(' ', 4*(LEVEL-1))|| first_name || ' ' || LAST_NAME "이름"
  FROM EMPLOYEES 
  START WITH manager_id is NULL
  CONNECT BY PRIOR employee_id=manager_id;
  
  --join사용
  select job_title, lpad(' ', 4*(LEVEL-1))|| first_name || ' ' || LAST_NAME "이름"
  FROM EMPLOYEES emp, jobs jb
  WHERE emp.JOB_ID = jb.JOB_ID 
  START WITH emp.manager_id is NULL
  CONNECT BY PRIOR emp.employee_id=emp.manager_id;
  ```



## PL/SQL개념

### PL/SQL(Procedure language/SQL)

* 오라클에서 제공하는 프로그래밍 언어
* 일반 프로그래밍 언어적인 요소를 다 가지고 있고, 데이터베이스 업무를 처리하기 위한 최적화된 언어

### 기본구조

* 기본구조

  * 선언부(Declare): 모든 변수나 상수 선언하는 부분
  * 실행부(Executable)
    * begin~end(실제 로직 처리하는 부분)
    * 제어문, 반복문, 함수정의등의 로직 기술하는 부분
  * 예외처리부(exception):실행 도중 에러 발생시 해결하기 위한 명령들을 기술하는 부분(생략 가능한 부분)

* Declare, begin, exception 키워드들은 ;을 붙이지 않는다

  나머지 문장들은 ;으로 처리

* 익명 블록(anonymous PL/SQL Block)

  * 주로 일회성으로 사용할 경우 많이 사용

* 저장 블록(stored PL/SQL Block)

  * 서버에 저장해 놓고 주기적으로 반복해서 사용할 경우 사용

### 사용방법

* 익명 블록

  ```sql
  SET serveroutput ON;-- 화면 출력을 하겠다는 의미
  DECLARE 
  cnt integer;
  BEGIN 
  	cnt:= cnt+1; --할당 연산자
  	IF cnt IS NULL THEN
  		dbms_output.put_line('결과: cnt는 널이다');--괄호 안 내용 화면 출력
  	END IF;
  END;
  /
  ```

  * 할당 연산자: `=`이 아니라 `:=`
  * DBeaver의 경우 `ctrl+shift+o`를 통해 server output 콘솔 사용 가능
  * 익명 블록: 일회성으로 프로시져를 만들어서 사용

* 익명블록을 오라클에서 어떻게 실행하는가

  * PL/SQL 엔진: 블록 처리

  * PL블록 엔진 안에 SQL문장 들어갈 수 있음

  * PL블록

    * PL/SQL
      * SQL문장-SQL문을 처리하기 위해서 오라클 서버에서 SQL문을 실행하기 위한 실행자가 따로 있음(SQL 처리자)
      * PL문장을 프로시저를 통해서 SQL문이 아닌 다른 프로시저는 프로시저가 실행자가 따로 있음(PL/SQL 엔진이 처리)

  * PL/SQL 실행 구조

    * PL/SQL문장->SQL문장->SQL실행자가 처리(오라클 서버)->프로시저 실행자에게 넘겨줌
    * PL SQL블록(PL/SQL문장->프로시저->프로시저 실행자가 처리)->PL/SQL엔진이 처리

  * 예제

    ```sql
    DECLARE 
    	empNo NUMBER(20);
    	empName varchar2(10);
    BEGIN
    	SELECT EMPLOYEE_ID , FIRST_NAME INTO empNo, empName
    	FROM EMPLOYEES 
    	WHERE EMPLOYEE_ID =124;
    
    	dbms_output.put_line(empNo||' '||empName);
    END;
    ```

    * PL/SQL문장 가져옴->SQL문->SQL 실행자 처리->PL처리자->전체 PL처리자가 처리



## PL/SQL변수 선언 및 데이터 타입

### 변수

* 변수의 생성 규칙

  1. 반드시 문자로 시작해야 한다
  2. 문자나 숫자, 특수문자를 포함 할 수 있다
  3. 변수명은 30bytes 이하여야 한다.(영문 30글자 정도)
  4. 예약어(키워드)를 사용하면 안된다

* 변수의 선언은 선언부(delare)에서 선언되고, 값으로 초기화 가능

* 실행부에서 실행될 경우 값이 할당 된다

* 서브프로그램의 파라미터로 전달되기도 하며, 서브프로그램의 출력 결과를 저장하기도 한다

* 변수 선언 예

  * emp_no number(6,3): 숫자를 저장하는 변수로 총 6자리 의미하며, 소수점 이하 3자리 의미
  * emp_name varchar2(5): 문자를 저장하는 변수로 총 바이트 길이가 5바이트 저장 가능
  * emp_date date : 날짜를 저장하는 변수

* 변수의 데이터 타입

  * char : 고정길이의 문자 저장, 기본최소값 1, 최대 32,767바이트 저장

  * varchar2: 가변길이의 문자 저장, 기본값은 없다. 최대 32,767바이트 값을 저장

  * number(전체자리수, 소수점이하 자리수)

    * 전체자리수와 소수점이하 자리 수를 가진 숫자를 저장
    * 전체 자리수 범위: 1~38까지 가능
    * 소수점 자리수 범위 -84~127까지 가능

  * binary_double: 부동 소수점 수를 저장, 9바이트 필요함

  * data

    * 날짜 및 시간을 저장, 초단위로 저장
    * 날짜의 범위 4712B.c~999 A.D

  * timestamp

    *  date 타입을 확장, 연도, 월, 일, 시, 분, 초 및 소수로 표시되는 초단위 저장
    *  자리수를 표현할때는 0~9범위의 정수 사용
    *  기본값은 6으로

  * **참조변수**

    * 형식: 테이블명.필드명%Type

    * ex)empNo employees.employ_id%TYPE->employ_id의 타입을 참조한다는 의미

      : employees 테이블의 employee_id와 동일한 데이터 타입으로 선언

    * ex) empRow employees$ROWTYPE

      : employees테이블의 모든 컬럼의 형식을 한꺼번에 저장하기 위한 변수로 선언

### 사용방법

* 출력 결과물 화면에 보이기

  * SQL Developer

    ```sql
    set serveroutput on;
    ```

  * DBeaver

    `ctrl+shift+o`

* PL/SQL 예시

  ```sql
  --table 복사
  CREATE TABLE employees1 AS 
  SELECT employee_id, salary, department_id
  FROM employees;
  
  --PL/SQL실행
  DECLARE 
  empNo employees1.employee_id%TYPE;
  empSalary employees1.salary%TYPE;
  
  BEGIN
  	SELECT employee_id, salary
  	INTO empNo, empSalary
  	FROM employees1
  	WHERE department_id=10;
  
  dbms_output.put_line(empNo||' '||empSalary);
  
  END;
  ```

* rowType예시

  ```sql
  DECLARE
  emp_row employees1%RowType;
  
  BEGIN
  	SELECT * INTO emp_row
  	FROM employees1
  	WHERE employee_id=100;
  dbms_output.put_line(emp_row.employee_id||' '|| emp_row.salary||' '||emp_row.department_id);
  END;
  ```

* RowType활용하여 입력하는 방법

  ```sql
  --테이블 생성
  CREATE TABLE row_test(
  	no NUMBER,
  	name varchar2(20),
  	hdate date
  );
  
  --테이블 복사
  CREATE TABLE row_test2 AS SELECT * FROM row_test;
  
  --데이터 입력
  INSERT INTO row_test values(1,'아무개',sysdate);
  INSERT INTO row_test values(2,'홍길동',sysdate);
  INSERT INTO row_test values(3,'고길동',sysdate);
  COMMIT;
  SELECT * FROM row_test;
  SELECT * FROM row_test2;
  
  --pl/sql
  DECLARE
  c_rec row_test%RowType;
  
  BEGIN
  	SELECT * INTO c_rec
  	FROM ROW_TEST 
  	WHERE NO=3;
  
  	INSERT INTO row_test2
  	VALUES c_rec;
  END;
  
  SELECT * FROM row_test2;
  ```



## rowType 변수 및 복합변수 활용 예

* rowType 변수를 활용한 데이터의 변경

  ```sql
  DECLARE
  c_rec row_test%rowType;
  BEGIN
  	SELECT * INTO c_rec
  	FROM row_test
  	WHERE NO=1;
  
  	c_rec.name :='강길동';
  	
  	UPDATE row_test2
  	SET row=c_rec -- 행전체를 바꿈
  	WHERE NO = 3;
  END;
  ```

* 사용자로부터 두 개의 숫자를 입력 받아서 합을 구하는 예

  ```sql
  DECLARE
  	no1 NUMBER:= &no1;
  	no2 NUMBER:= &no2;
  	vsum NUMBER;
  BEGIN
  	vsum:=no1+no2;
  	dbms_output.put_line('첫번째 수:'||no1||', 두번째 수:'||no2||', 합: '||vsum||'입니다');
  END;
  ```

  * 치환 연산자 &사용->DBeaver에서 안됨

* 복합변수

  * record Type 변수 지정 방법

    1.  type 타입명 is record()

    2.  식별자 타입명 
        * 하나의 식별자로 만들어서 사용

  ```sql
  DECLARE 
  TYPE emp_rec IS record
  (
  	emp_id employees.employee_id%TYPE,
  	emp_name employees.first_name%TYPE,
  	emp_job employees.job_id%type
  );
  --레코드 변수가 됨
  
  rec1 emp_rec;
  
  BEGIN 
  	SELECT employee_id, first_name, job_id
  	INTO rec1
  	FROM employees
  	WHERE department_id=10;
  
  dbms_output.put_line('사번    이름    업무아이디');
  dbms_output.put_line(rec1.emp_id||' '||rec1.emp_name||' '||rec1.emp_job);
  END;
  ```

* ```sql
  DECLARE
  TYPE emp_rec2 IS record(
  	emp_id employees.employee_id%TYPE,
  	emp_name employees.last_name%TYPE,
  	emp_email employees.email%TYPE,
  	emp_salary employees.salary%Type
  );
  
  rec2 emp_rec2;
  
  --사용자로 부터 받기
  vemp_id employees.employee_id%TYPE := '&empid';
  
  BEGIN
  	SELECT employee_id, last_name, nvl(email,'없음'), salary
  	INTO rec2
  	FROM employees
  	WHERE employee_id = vemp_id;
  
  dbms_output.put_line('사번: '||rec2.emp_id);
  dbms_output.put_line('이름: '||rec2.emp_name);
  dbms_output.put_line('이메일: '||rec2.emp_email);
  dbms_output.put_line('급여: '||rec2.emp_salary);
  END;
  /
  ```



## 콜렉션, 바인드 변수 사용

### Table Type 변수(컬렉션)

* 컬렉션

  * 일반 프로그래밍 언어에서 사용하는 배열 타입을 PL/SQL에서는 **컬렉션**이라고 한다

* 종류

  * 연관배열(associative array/index-by table)
    * 키와 값의 쌍으로 구성된 컬렉션, java의 해시테이블과 같은 개념
    * key의 데이터 유형
      * 숫자: binary_integer, pls_integer
        * 위 두 가지 데이터 타입은 number보다 작은 저장 영역 필요, 산술 연산의 경우 number보다 빠르다
      * 문자: varchar2
    * 값(value)
      * 일반 데이터 타입, 레코드 타입이 값이 될 수 있다
      * 레코드 타입일 경우 여러 개의 값을 가질 수 있다
  * varry(variable array)
    * 고정 길이를 가진 배열(순서 유지)
    * 일반 프로그래밍에서 사용하는 배열과 같다
  * 중첩테이블(nested table)
    * varry와 흡사한 구조의 배열(순서 유지x)
    * 배열의 크기를 명시하지 않음, 동적으로 배열의 크기가 설정

* Table 타입의 선언 형식

  1. 정의

     ```sql 
     Type 타입명 IS TABLE OF
     employees.first_name&type
     INDEX BY BINARY_INTEGER;
     ```

  2. 선언(메모리화)-실제 메모리를 잡아서 변수로 공간 확보

     식별자 타입명;

* 실습

  ```sql
  SET serveroutput ON;
  
  ```

  

## 조건문(if문, case문)



## 반복문(basic loop, while, for loop, continue)



## 서브프로그램, 패키지, 테이블 스페이스

### 테이블 스페이스 이해

#### 테이블 스페이스

* 오라클에서 데이터를 저장할 때 사용하는 **논리적 저장공간** (하드디스크에서 실제 여러 개의 물리적 데이터 파일로 구성될 수 있음). 오라클 시스템 운영에 필요한 필수 정보 담고 있음
  * 데이터베이스 안에 여러 개의 테이블 스페이스로 구성. 
  * 테이블 스페이스 안에는 여러 개의 세그먼트로 구성

* 시스템 테이블 스페이스
  * Data Dictionary 정보, 프로시저, 트리거, 패키지, 시스템 rollback segment, 사용자 데이터 포함
    * 시스템을 운영/관리하기 위한 세그먼트( 프로시저, 트리거, 패키지, 시스템 rollback segment 등)+사용자 데이터(일반적으로 만든 테이블)
  * 기본적으로 자동으로 갖고 있는 테이블 스페이스
* Non-system 테이블 스페이스
  * Temporary 세그먼트, application Data 세그먼트, index 세그먼트, 사용자 데이터 세그먼트
    * 관리와는 상관없는 세그먼트 
    * Temporary세그먼트: 데이터 정렬을 하기 위한 공간

#### 테이블 스페이스 구성

* 세그먼트(segment)
  * 익스텐트(extent)-연속적인 데이터블록으로 구성
    * 데이터블록: 오라클 입출력 최소 단위 (논리적 저장 최소단위)

#### 테이블 스페이스 구문

* 테이블 스페이스 생성

  ```sql
  create tablespace 테이블스페이스명
  	datafile '저장될 경로 및 사용할 파일명'
  	size 저장공간
  	default storage storage_clause;
  ```

  * 테이블스페이스 오브젝트로 생성하면 하나의 파일(.dbf, .ora)로 저장

* 테이블스페이스 삭제

  ```sql
  drop tablespace 테이블스페이스이름
  [including contents [and datafiles]]
  [cascade constraints];
  ```

#### 실습

* 데이터베이스 접속
  * host: localhost
  * database(SID): orcl
  * 사용자이름/비밀번호: system/1234

* tablespace 생성

  ```sql
  CREATE tablespace test_1
  	datafile 'c:\oradata\test_1.dbf' SIZE 10M
  	DEFAULT storage (--extent관련 세부 옵션
  		INITIAL 1M --최초 크기
  		NEXT 1M
  		MINEXTENTS 1
  		MAXEXTENTS 10
  		PCTINCREASE 0 );
  ```

  * `c:\oradata` 폴더 생성이 선행돼야 함

* tablespace공간 늘리기

  ```sql
  ALTER tablespace test_1
  	ADD datafile 'c:\oradata\test_2.dbf' SIZE 10M;
  ```

* tablespace삭제

  ```sql
  --test_1 테이블스페이스에 aa테이블 생성
  CREATE TABLE aa(
  	name varchar2(10)
  ) tablespace test_1;
  
  --테이블 스페이스 삭제
  DROP tablespace test_1;--테이블스페이스가 비어있지 않으므로 including contents 옵션 필요
  
  DROP tablespace test_1
  	INCLUDING contents;--파일은 남아 있음
  
  --데이터 파일까지 삭제옵션포함
  DROP tablespace test_1
  	INCLUDING contents AND datafiles;
  ```

  

### 테이블 스페이스 관리



## 사용자 관리와 권한

### 사용자 관리(생성, 변경, 삭제) 및 사용자 정보 알아보기

### 비밀번호 관리

### 시스템 권한의 이해

### 오브젝트 권한의 이해

### 롤의 이해

