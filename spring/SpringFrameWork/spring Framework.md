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

       ![tomcat 설정](..\..\assets\tomcat 설정.PNG)

  4. Eclipse에 스프링 plugin설치

     * help->eclipse Marketplace-> find:STS로 검색

## 개발환경 구축 및 프로젝트 만들기



## 스프링의 특징 및 DI 기본 개념

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

