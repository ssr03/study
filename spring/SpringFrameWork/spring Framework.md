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
    * 객체 a가 객체 b와 c의 기능을 사용할 때, a에서 객체를 직접 생성하는 것이 아니라 외부 제 3자(제 3의 객체 or 클라이언트)에 의해서 만들어 놓은 객체를 사용
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
  * 특정 인터페이스나 클래스를 상속받지 않는 순수한 자바 객체를 스프링 컨테이너가 저장
* 트랜잭션 처리를 위한 일관된 방식 제공
* 영속성(persistence)과 관련된 다양한 API 제공
  * JDBC, IBatis, MyMatis, JPA, Hibernate 등과 같은 프레임워크와 연동 지원

### DI(Dependency Injection) 의존성 주입

* DI 스프링의 핵심 개념 중 하나
* 객체 사이의 의존 관계를 객체 자신이 아닌 외부(스프링 컨테이너)에서 수행하는 개념
* 의존관계의 설정은 설정파일(`bean.xml`) or `어노테이션`을 이용하여 설정



## 인터페이스를 이용한 의존성 낮추기

* AA 객체에서 BB 객체를 생성

  ```java
  package com.test.spring;
  
  public class BB {
  	public void aa() {
  		System.out.println("BB객체의 aa() 메서드 입니다!");
  	}
  }
  ```

  ```java
  package com.test.spring;
  
  public class AA {
  	BB bb = new BB();
  	
  	public void print() {
  		bb.aa();
  	}
  }
  ```

  * AA는 BB에 의존한다(AA has a BB)
    * AA객체는 bb라는 객체를 직접 가지고 있음

* 그렇다면 DI는 의존하는 객체에 대한 획득을 클래스에서 하지 않고, 스프링 컨테이너가 주입(제공)해 준다.

  * 개발자는 AA 클래스에서 `BB bb = new BB();`를 사용하지 않고 스피링 컨테이너가 AA 클래스를 생성할 때 생성하는 BB클래스의 인스턴스(bean)를 주입 받는다(의존성을 낮추기 위해)
  * 설정은 xml 설정을 통해서 이루어진다

* 인터페이스를 이용한 의존성을 낮춤:

  ```
  service 객체--------------------------> 인터페이스 DAO
  DAO dao									^		^
  										aDao	bDao
  
  dao = new aDao()
  dao = new bDao()
  ```

  * 위의 그림은 의존성이 낮아짐

  * 코드의 변경 없이 xml의 설정만으로 개발자가 원하는 객체의 주입으로 바꿀 수 있음

  * 실습

    * `com.test.di`패키기 생성

      * `TestDAO` interface생성

        ```java
        package com.test.di;
        
        public interface TestDAO {
        	void printMsg();
        }
        ```

      * `TestDAOImp`

        ```java
        package com.test.di;
        
        public class TestDAOImp implements TestDAO{
        
        	@Override
        	public void printMsg() {
        		System.out.println("TestDAOImp의 printMsg()메소드 입니다~");
        		
        	}
        
        }
        ```

    * `src/main/resource`에서 `Spring Bean Configuration File`로 `test.xml`설정 파일 만들기

      ```xml
      ...
      <bean id="TestDAOImp" class="com.test.di.TestDAOImp"/>
      ...
      ```

      * new->Spring Bean Configuration File

    * 결과 확인을 위한 `TestMain`클래스 생성

      ```java
      package com.test.di;
      
      import org.springframework.context.support.AbstractApplicationContext;
      import org.springframework.context.support.GenericXmlApplicationContext;
      
      public class TestMain {
      	public static void main(String[] args) {
      		
      		String confLoc = "classpath:test.xml";
      		AbstractApplicationContext ctx = new GenericXmlApplicationContext(confLoc);
      		TestDAO testDAO = ctx.getBean("TestDAOImp", TestDAOImp.class);
      		
      		System.out.println("==========");
      		testDAO.printMsg();
      		System.out.println("==========");
      	}
      }
      ```

      * `TestDAO testDao = new TestDAOImp()`코드를 사용하지 않았음에도 불구하고, `testDao.printMsg()`사용



## Bean 설정 파일 개념 및 DI구현

* `com.test.diEx01`패키지 생성

  * `GetSum.java`

    ```java
    package com.test.diEx01;
    
    public class GetSum {
    
    	public void sum(int aa, int bb) {
    		System.out.println("더하기");
    		int result = aa + bb;
    		System.out.println("합:"+result);
    	}
    }
    ```

  * `MyGetSum.java`

    ```java
    package com.test.diEx01;
    
    public class MyGetSum {
    	public GetSum getsum;
    	private int a;
    	private int b;
    	
    	public MyGetSum() {
    	}
    	
    	public void sum() {
    		getsum.sum(a,b);
    	}
    
    	public void setGetsum(GetSum getsum) {
    		this.getsum = getsum;
    	}
    
    	public void setA(int a) {
    		this.a = a;
    	}
    
    	public void setB(int b) {
    		this.b = b;
    	}
    }
    ```

  * `src/main/resources`에 `getsum.xml`생성(spring bean configuration file)

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
    	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    	
    	<bean id="getsum" class="com.test.diEx01.GetSum"/>
    	<bean id="myGetSum" class="com.test.diEx01.MyGetSum">
    		<property name="getsum">
    			<ref bean="getsum"/>
    		</property>
    		<property name="a" value="10"/>
    		<property name="b" value="100"/>
    	</bean>
    </beans>
    ```

    * bean은 객체(instance)와 같음
    * `ref`는 참고하고자 하는 bean을 의미

  * `Main.java`

    ```java
    package com.test.diEx01;
    
    public class Main {
    
    	public static void main(String[] args) {
    		// MyGetSum 내용 불러오기
    		MyGetSum myGetSum = new MyGetSum();
    		myGetSum.setGetsum(new GetSum());
    		
    		myGetSum.setA(10);
    		myGetSum.setB(100);
    		
    		myGetSum.sum();
    	}
    }
    ```

    * GetSum 클래스를 Main에서 직접 생성하여 의존에 대한 책임을 Main이 하고 있음
    * Spring 에서는 xml에서 의존 관계를 가짐

  * `Main.java`

    ```java
    package com.test.diEx01;
    
    import org.springframework.context.support.AbstractApplicationContext;
    import org.springframework.context.support.GenericXmlApplicationContext;
    
    public class Main {
    
    	public static void main(String[] args) {
    
    		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:getsum.xml");
    		MyGetSum myGetSum = ctx.getBean("myGetSum", MyGetSum.class);
    		
    		myGetSum.sum();
    	}
    }
    ```

    

## 생성자 설정을 이용한 DI구현

### 의존성 주입 종류

1. setter(설정 메소드)를 이용한 주입
   * 설정 메소드를 사용해서 의존성을 주입하는 것
2. Constructor(생성자)를 통한 주입
   * 생성자를 사용해서 의존성을 주입하는 것

### Constructor을 통한 주입

* `com.test.diEx02` 패키지 생성

  * `ExamDoa.java`

    ```java
    package com.test.diEx02;
    
    public class ExamDao {
    	private String msg;
    	
    	public ExamDao() {};	//기본 생성자
    	
    	public ExamDao(String msg) {	//인자 생성자
    		this.msg = msg;
    	}
    	
    	public void outputMsg() {
    		System.out.println("msg: "+msg);
    	}
    }
    ```

  * `examDao.xml`: 설정파일(Spring bean configuration file)

    ```xml
    <bean id="examDao" class="com.test.diEx02.ExamDao">
        <constructor-arg value="Hello Spring!"/>
    </bean>
    ```

    * `contructor-arg`태그를 사용하지 않으면, 기본 생성자를 토대로 생성하게 됨

  * `MainExam.java`

    ```java
    package com.test.diEx02;
    
    import org.springframework.context.support.AbstractApplicationContext;
    import org.springframework.context.support.GenericXmlApplicationContext;
    
    public class MainExam {
    
    	public static void main(String[] args) {
    		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:examDao.xml");
    		ExamDao examDao = ctx.getBean("examDao", ExamDao.class);
    		
    		examDao.outputMsg();
    	}
    	
    }
    ```

    * `getBean`에서 불러 올 때는 해당 id값으로 불러온다

* `com.test.diEx03` 패키지 생성

  * `Person.java`

    ```java
    package com.test.diEx03;
    
    public class Person {
    
    	private String name;
    	private String gender;
    	private String age;
    	private String idNo;
    	
    	//기본 생성자
    	public Person() {}
    	
    	//인자 생성자
    	public Person(String name, String gender, String age, String idNo) {
    		this.name = name;
    		this.gender = gender;
    		this.age = age;
    		this.idNo = idNo;
    	}
    
    	//setter, getter
        ...
    }
    ```

  * `PersionInfo.java`: 사람에 대한 정보 출력해 주는 클래스

    ```java
    package com.test.diEx03;
    
    public class PersonInfo {
    	private Person person;
    	
    	//기본생성자
    	public PersonInfo(){}
    	
    	//인자 생성자
    	public PersonInfo(Person person) {
    		this.person = person;
    	}
    	
    	public void getPersoInfo() {
    		if(person != null) {
    			System.out.println("이름: "+person.getName());
    			System.out.println("성별: "+person.getGender());
    			System.out.println("나이: "+person.getAge());
    			System.out.println("주민번호: "+person.getIdNo());
    			System.out.println("----------------------------");
    		}
    	}
    	
    	public void setPerson(Person person) {
    		this.person = person;
    	}
    	
    }
    ```

  *  `person.xml`설정 파일(Spring bean configuration file)

    ```xml
    <bean id="person1" class="com.test.diEx03.Person">
        <constructor-arg>
            <value>홍길동</value>
        </constructor-arg>
        <constructor-arg>
            <value>남</value>
        </constructor-arg>
        <constructor-arg>
            <value>26</value>
        </constructor-arg>
        <constructor-arg>
            <value>88888-123456</value>
        </constructor-arg>
    </bean>
    <bean id="person2" class="com.test.diEx03.Person">
        <constructor-arg>
            <value>홍길서</value>
        </constructor-arg>
        <constructor-arg value="여"/>
        <constructor-arg value="15"/>
        <constructor-arg value="9241212-2511515"/>
    </bean>
    <bean id="personInfo" class="com.test.diEx03.PersonInfo">
        <constructor-arg>
            <ref bean="person1"/>
        </constructor-arg>
    </bean>
    ```

  * `MainPerson.java`

    ```java
    package com.test.diEx03;
    
    import org.springframework.context.support.AbstractApplicationContext;
    import org.springframework.context.support.GenericXmlApplicationContext;
    
    public class MainPerson {
    
    	public static void main(String[] args) {
    		String confLoc = "classpath:person.xml";
    		
    		AbstractApplicationContext ctx = new GenericXmlApplicationContext(confLoc);
    		PersonInfo personInfo = ctx.getBean("personInfo", PersonInfo.class);
    		personInfo.getPersoInfo();
            
            //자원 반납
    		ctx.close();
    	}
    }
    ```

    * person1에 대한 정보를 가져온다



## 프로퍼티 설정을 이용한 DI구현

* 실무에서는 의존관계를 설정할 때, 생성자보다는 프로퍼티 설정을 더 많이 사용

* `com.test.diEx04`패키지 생성

  * `MyBatisDao.java`

    ```java
    package com.test.diEx04;
    
    public class MyBatisDao {
    	// 기본 생성자
    	public MyBatisDao() {}
    	
    	public void insertDB() {
    		System.out.println("insert 로직...");
    	}
    	
    	public void updateDB() {
    		System.out.println("update 로직");
    	}
    }
    ```

  * `MyBatisService.java`

    ```java
    package com.test.diEx04;
    
    public class MyBatisService {
    	private MyBatisDao myBatisDao;
    	
    	// setter
    	public void setMyBatisDao(MyBatisDao myBatisDao) {
    		this.myBatisDao = myBatisDao;
    	}
    	
    	public void myBatisTest() {
    		System.out.println("================");
    		myBatisDao.insertDB();
    		myBatisDao.updateDB();
    		System.out.println("================");
    	}
    }
    ```

    * `MyBatisDao`를 의존하는 서비스 객체

  * `myBatis.xml`(spring bean configuration file)

    * property 생성방식

      ```xml
      <bean id="myBatis" class="com.test.diEx04.MyBatisDao"></bean>
      
      <bean id="service" class="com.test.diEx04.MyBatisService">
          <!-- 프로퍼티 설정방식 -->
          <property name="myBatisDao" ref="myBatis"></property>
      </bean>
      ```

      * setter 메서드(ex. `setMyBatisDao`)에서 set을 제외한 메소드명을 property name으로 사용
      * 해당 클래스의 `setMyBatisDao()`를 호출하는 것
      * 이러한 방식을 **프로퍼티 설정방식**이라 한다

    * xml의 네임 스페이스 방식 

      ```xml
      <beans xmlns="http://www.springframework.org/schema/beans"
      	...
      xmlns:p="http://www.springframework.org/schema/p"
      	...
             >
      	<bean id="myBatis" class="com.test.diEx04.MyBatisDao"></bean>
      
      	<bean id="service" class="com.test.diEx04.MyBatisService" p:myBatisDao-ref="myBatis">	
      	</bean>
      </beans>
      ```

      * xml의 네임 스페이스 방식을 이용하면 property 태그 사용x
      * "p:프로퍼티 이름", "p: 프로퍼티이름-ref='..'"속성 사용

  * `MainMyBatis.java`

    ```java
    package com.test.diEx04;
    
    import org.springframework.context.support.AbstractApplicationContext;
    import org.springframework.context.support.GenericXmlApplicationContext;
    
    public class MainMyBatis {
    
    	public static void main(String[] args) {
    		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:myBatis.xml");
    		MyBatisService service = ctx.getBean("service", MyBatisService.class);
    		
    		service.myBatisTest();
    		
    		ctx.close();
    	}
    
    }
    ```

    

## DI 사용의 장점

### 의존성 주입(DI)의 장점

* DI를 사용하면, 개발 계획시에 시간이 요구가 되지만, 규모가 큰 프로젝트에서 유지보수 업무를 한다면 DI개발의 장점을 느낄 수 있다.

### 실습

* `com.test.diEx05` 패키지 생성

  *  `Car.java` intereface 

    ```java
    package com.test.diEx05;
    
    public interface Car {
    	public void drive();
    }
    ```

  * Car를 상속 받는 클래스

    ```java
    package com.test.diEx05;
    
    public class HatchbackCar implements Car{
    	@Override
    	public void drive() {
    		System.out.println("해치백 운전합니다!");
    	}
    }
    ```

    ```java
    package com.test.diEx05;
    
    public class SedanCar implements Car{
    	@Override
    	public void drive() {
    		System.out.println("세단을 운전합니다!");	
    	}
    }
    ```

    ```java
    package com.test.diEx05;
    
    public class SUVCar implements Car{
    
    	@Override
    	public void drive() {
    		System.out.println("SUV를 운전합니다");
    	}
    }
    ```

  * `car.xml`

    ```xml
    <bean id="car" class="com.test.diEx05.SUVCar"/>
    ```

  * `CarTester.java`

    ```java
    package com.test.diEx05;
    
    import org.springframework.context.support.AbstractApplicationContext;
    import org.springframework.context.support.GenericXmlApplicationContext;
    
    public class CarTester {
    
    	public static void main(String[] args) {
    		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:car.xml");
    		Car car =ctx.getBean("car", Car.class);
    		car.drive();
    	}
    }
    ```

    * 설정파일에서 class만 변경하여 차량 변경 가능

      -> 변경사항 쉽게 유지보수 가능



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

