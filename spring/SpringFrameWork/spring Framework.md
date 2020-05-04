# Spring Framework Basic

## 스프링 개요

* framework

  * 개발에 필요한 API제공
  * 공통적으로 개발해야 할 것들 제공

* 선수 학습

  * java 언어, jsp/servlet, 스크립트언어(html, javascript, jquery, css)

* 개념

  * 자바언어를 기반으로 한 애플리케이션을 제작할 때, 효율적으로 빠르게 개발할 수 있도록 하는 애플리케이션 프레임워크이다. (프로그래밍 틀) 

* 개발환경

  1. JDK설치(환경변수 설정)

  2. 개발 툴 설치

     * Eclipse(java EE버전), STS(spring tool suit)

  3. 웹컨테이너 설치:

     * http://tomcat.apache.org

     * 톰갯 설치(7.0, 8.0) (이클립스 연동)

       * eclispe->window->show view->server
       * server location
         * use tomcat installation
       * port:9090으로 변경
       * serer options(check)
         * [x] publish module contexts to separate XML files 
         * [x] modules auto reload by default

       <img src="..\..\assets\tomcat 설정.PNG" alt="tomcat 설정" style="zoom:80%;" />

  4. Eclipse에 스프링 plugin설치

     * help->eclipse Marketplace-> find:STS로 검색
     * STS URL: http://spring.io/

* spring의 중요 개념

  * **DI(dependency Injection)**
    * 의존성 주입
    * 객체 a가 객체 b와 c의 기능을 사용할 때, a에서 객체를 직접 생성하는 것이 아니라 외부 제 3자(제 3의 객체 or 클라이언트)에 의해서 만들어 놓은 객체를 사용
  * **IoC**

## 개발환경 구축 및 프로젝트 만들기

* eclipse->window->open perspective->spring
* 프로젝트 생성
  * file->new->spring legacy project
  * spring maven선택
* `com.test.spring`패키지 생성

## 스프링의 특징 및 DI 기본 개념

### 스프링 프레임워크란?

* 스프링은 엔터프라이즈 애플리케이션에서 필요로 하는 여러가지 기능들을 제공하는 프레임워크
* J2E가 제공하는 기능들을 스프링에서 지원 하고 있기 때문에 국내에서 가장 인기 있는 프레임워크로 자리를 잡음
* 스프링은 J2E에서 제공하는 기능 외에 DI나 AOP와 같은 기능을 지원

### 스프링 프레임워크 특징

* 스프링은 경량의 프레임워크
  * 자바의 객체를 담고 있는 컨테이너
  * 객체의 생성, 소멸과 같은 생명주기를 관리
* DI(Dependency Injection): 의존성 주입 패턴 지원
  * 설정파일을 통해서 의존관계를 설정해 주는 패턴
* AOP(Aspect Oriented Programming)을 지원
  * 트랜잭션이나, 로깅, 보안과 같은 엔터프라이즈 애플리케이션에서 공통으로 필요로 하는 기능을 분리해서 각 모듈에 적용할 수 있도록 하는 기능
* 스프링은 POJO(Plain Old Java Object) 지원
  * 특정 인터페이스나 클래스를 상속받지 않는 순수한 자바 객체를 스프링 컨테이너가 저장
* 트랜잭션 처리를 위한 일관된 방식 제공
* 영속성(persistence)과 관련된 다양한 API 제공
  * JDBC, IBatis, MyMatis, JPA, Hibernate 등과 같은 프레임워크와 연동 지원

### DI(Dependency Injection) 의존성 주입

* DI 스프링의 핵심 개념 중 하나
* 객체 사이의 의존 관계를 객체 자신이 아닌 외부(스프링 컨테이너)에서 수행하는 개념
* 의존관계의 설정은 설정파일(`bean.xml`) or `어노테이션`을 이용하여 설정

## 인터페이스를 이용한 의존성 낮추기

## Bean 설정 파일 개념 및 DI구현

## 생성자 설정을 이용한 DI구현

## 프로퍼티 설정을 이용한 DI구현

## DI 사용의 장점

## XML을 이용한 의존관계 설정

## 자바코드를 이용한 의존관계 설정

## xml과 자바코드 혼용한 의존관계 설정

## 빈의 Life Cycle

## 빈의 범위(Scope)

## 사용자 초기화 메소드 및 사용자 소멸 메소드 설정

## Environment를 이용한 빈 설정

## XML에 외부 properties파일 불러오기

## java코드에 외부 properties파일 불러오기

## Profile속정 이용한 빈설정

## IoC 개념 정리

## AOP 개념 이해

## XML 스키마 기반의 AOP

## Advice 타입별 AOP

## @Aspect 어노테이션을 이용한 AOP

