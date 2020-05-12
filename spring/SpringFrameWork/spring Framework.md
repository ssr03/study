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

  * 개발자는 AA 클래스에서 `BB bb = new BB();`를 사용하지 않고 스프링 컨테이너가 AA 클래스를 생성할 때 생성하는 BB클래스의 인스턴스(bean)를 주입 받는다(의존성을 낮추기 위해)
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

  *  `Car.java` intereface 

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

### 의존 관계 설정 방법

* XML 파일을 이용한 설정 방법
* JAVA를 이용한 설정 방법
* XML과 JAVA를 혼용해서 사용하는 방법

### 실습

* `com.test.diEx06`패키지 생성

  * `Player.java`

    ```java
    package com.test.diEx06;
    
    import java.util.ArrayList;
    
    public class Player {
    	private String name;
    	private int age;
    	private ArrayList<String> position;
    	private double height;
    	private double weight;
    	
    	public Player() {}
    	
    	public Player(String name, int age, ArrayList<String> position) {
    		this.name = name;
    		this.age = age;
    		this.position = position;
    	}
        ...
        //setter, getter
    }
    ```

  * `PlayerInfo.java`

    ```java
    package com.test.diEx06;
    
    public class PlayerInfo {
    	public Player player;
    	
    	public PlayerInfo() {} 
    	
    	public void setPlayer(Player player) {
    		this.player = player;
    	}
    	
    	public Player getPlayer() {
    		return player;
    	}
    }
    ```

  * `BaseBallTeam.java`

    ```java
    package com.test.diEx06;
    
    public class BaseBallTeam {
    	String manager; //야구감독
    	String battingCoach; 
    	String pitchingCoach;
    	String hitter;
    	String pitcher;
    	
    	public BaseBallTeam() {}
    	
    	public BaseBallTeam(String manager, String battingCoach, String pitchingCoach) {
    		this.manager = manager;
    		this.battingCoach = battingCoach;
    		this.pitchingCoach = pitchingCoach;
    	} //인자 생성자
    
    	// getter, setter
        ...
    }
    ```

  * `baseBall1.xml`(Spring Bean Configuration file)

    ```xml
    <bean id="player1" class="com.test.diEx06.Player">
        <constructor-arg value="박병호"/>
        <constructor-arg value="28"/>
        <constructor-arg>
            <list>
                <value>4번</value>
                <value>1루수</value>
            </list>
        </constructor-arg>
        <property name="height">
            <value>188</value>
        </property>
        <property name="weight" value="80"/>
    </bean>
    
    <bean id="playerInfo1" class="com.test.diEx06.PlayerInfo">
        <property name="player">
            <ref bean="player1"/>
        </property>
    </bean>
    ```

  * `MainBaseBall.java`

    ```java
    package com.test.diEx06;
    
    import org.springframework.context.support.AbstractApplicationContext;
    import org.springframework.context.support.GenericXmlApplicationContext;
    
    public class MainBaseBall {
    
    	public static void main(String[] args) {
    		String confLoc = "classpath:baseBall1.xml";
    		AbstractApplicationContext ctx  = new GenericXmlApplicationContext(confLoc);
    		
    		Player player1 = ctx.getBean("player1", Player.class);
    		System.out.println(player1.getName());
    		System.out.println(player1.getPosition());
    		
    		PlayerInfo playerInfo = ctx.getBean("playerInfo1", PlayerInfo.class);
    		Player player2 = playerInfo.getPlayer();
    		System.out.println(player2.getName());
    		System.out.println(player2.getPosition());
    		
    		if(player1.equals(player2)) {
    			System.out.println("player1과 player2는 같은 선수!");
    		}
    
    	}
    
    }
    ```

  * `baseBall2.xml`(JavaBean Configration File)

    ```xml
    <bean id="player3" class="com.test.diEx06.Player">
        <constructor-arg value="강정호"/>
        <constructor-arg value="28"/>
        <constructor-arg>
            <list>
                <value>5번타자</value>
                <value>3루수</value>
            </list>
        </constructor-arg>
    
        <property name="height">
            <value>186</value>
        </property>
        <property name="weight">
            <value>80</value>
        </property>
    </bean>
    ```

  * `MainBaseBall.java`

    ```java
    package com.test.diEx06;
    
    import org.springframework.context.support.AbstractApplicationContext;
    import org.springframework.context.support.GenericXmlApplicationContext;
    
    public class MainBaseBall {
    
    	public static void main(String[] args) {
    		String confLoc = "classpath:baseBall1.xml";
    		String confLoc2 = "classpath:baseBall2.xml";
    		AbstractApplicationContext ctx  = new GenericXmlApplicationContext(confLoc, confLoc2);
            ...
            Player player3 = ctx.getBean("player3", Player.class);
    		System.out.println(player3.getName());
    		System.out.println(player3.getPosition());
        }
    }
    ```

    * 설정파일 두 개를 한꺼번에 적용 가능

  * `baseBall2.xml`

    ```xml
    <beans 
    	...
    	xmlns:c="http://www.springframework.org/schema/c"
    	xmlns:p="http://www.springframework.org/schema/p"
        ...
        >
        ...
        <bean id="baseBallTeam" class="com.test.diEx06.BaseBallTeam"
              c:manager="김응용" c:battingCoach="이순철" c:pitchingCoach="선동렬"
              p:hitter="강정호">
            <property name="pitcher" value="류현진"/>
        </bean>
    </beans>
    ```

    * namespace사용

  * `MainBaseBall.java`

    ```java
    ...
    System.out.println("===============야구팀 구성================");
    BaseBallTeam baseBallTeam = ctx.getBean("baseBallTeam", BaseBallTeam.class);
    System.out.println("감독: "+baseBallTeam.getManager());
    System.out.println("타격코치: "+baseBallTeam.getBattingCoach());
    System.out.println("투수코치: "+baseBallTeam.getPitchingCoach());
    System.out.println("타자: "+baseBallTeam.getHitter());
    System.out.println("투수: "+baseBallTeam.getPitcher());
    ```

    

## 자바코드를 이용한 의존관계 설정

### JAVA를 이용한 설정 방법(어노테이션을 이용)

* 어노테이션(Annotation:Metadata)
  * JDK5부터 등장한 개념 `ex) @Override`
  * 선언시에는 `@`를 사용하여 선언
  * 어노테이션을 사용하는 경우
    * 컴파일러에게 정보를 알려주거나 
    * 컴파일할 때와 설치(deployment)시의 작업을 지정하거나
    * 실행할 때 별도의 처리가 필요한 경우에 사용
  * 클래스, 메소드, 변수 등 모든 요소에 선언 가능
* `@Configuration`
  * 클래스 앞에 선언
  * "이 클래스는 스프링 설정에 사용되는 클래스 입니다"라고 알려주는 어노테이션
* `@Bean`
  * 메소드 앞에 사용
  * "객체를 생성"

### 실습

* `com.test.diEx07`패키지 생성

  * `Player.java`
    * `com.test.diEx06`의 `Player.java`복사

  * `ConfigApp.java`

    ```java
    package com.test.diEx07;
    
    import java.util.ArrayList;
    
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    
    @Configuration
    public class ConfigApp {
    
    	@Bean
    	public Player player1() {
    		ArrayList<String> position = new ArrayList<String>();
    		position.add("4번 타자");
    		position.add("3루수");
    		
    		Player player = new Player("박병호", 28, position);
    		player.setHeight(187);
    		player.setWeight(80);
    		
    		return player;
    	}
    	
    	@Bean
    	public Player player2() {
    		ArrayList<String> position = new ArrayList<String>();
    		position.add("3번 타자");
    		position.add("유격수");
    		
    		Player player = new Player("강정호", 28, position);
    		player.setHeight(186);
    		player.setWeight(79);
    		
    		return player;
    	}
    }
    ```

    * 설정 파일

  * `MainBaseBall.java`

    ```java
    package com.test.diEx07;
    
    import org.springframework.context.annotation.AnnotationConfigApplicationContext;
    
    public class MainBaseball {
    	public static void main(String[] args) {
    		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigApp.class);
    		
    		Player player1 = ctx.getBean("player1", Player.class);
    		System.out.println("선수이름: "+player1.getName());
    		System.out.println("나이: "+player1.getAge());
    		System.out.println("포지션: "+player1.getPosition());
    		System.out.println("신장: "+player1.getHeight());
    		System.out.println("몸무게: "+player1.getWeight());
    		
    		System.out.println("===================================");
    		
    		Player player2 = ctx.getBean("player2", Player.class);
    		System.out.println("선수이름: "+player2.getName());
    		System.out.println("나이: "+player2.getAge());
    		System.out.println("포지션: "+player2.getPosition());
    		System.out.println("신장: "+player2.getHeight());
    		System.out.println("몸무게: "+player2.getWeight());
    		
    		ctx.close();
    	}
    }
    ```

* `cglib.jar`추가

  * ```powershell
    Exception in thread "main" java.lang.IllegalStateException: CGLIB is required to process @Configuration classes.
    ```

    * 위와 같은 에러 발생 시, `CGLIB.jar` 가 없어서 발생한 Exception

    * `pom.xml`에 아래와 같은 dependency 추가

      ```xml
      <dependency>
          <groupId>cglib</groupId>
          <artifactId>cglib</artifactId>
          <version>2.2</version>
      </dependency>
      ```



## xml과 자바코드 혼용한 의존관계 설정

*  XML파일과 JAVA파일을 같이 사용해서 스프링 설정을 하는 방법
  1. xml 안에서 java코드 불러오는 방식
  2. java코드 안에서 xml 불어오는 방식

### XML안에 java코드 불러오는 방법

* `com.test.diEx08`패키지 생성

  * `com.test.diEx07`패키지에서 `Player.java`, `ConfigApp.java` 복사

  * `ConfigApp.java`

    * player2() bean 객체 제거->xml에서 생성
    * `@Configuration` x

  * `baseBall3.xml`(Spring Bean Configuration file)

    ```xml
    <beans ...
    	xmlns:context="http://www.springframework.org/schema/context"
    	xsi:schemaLocation="...
    	...
    	http://www.springframework.org/schema/context
    	http://www.springframework.org/schema/context/spring-context.xsd
        ">
    	
    	<context:annotation-config/>
    	<bean class="com.test.diEx08.ConfigApp"/>
    	
    	<bean id="player2" class="com.test.diEx08.Player">
    		<constructor-arg value="강정호"/>
    		<constructor-arg value="28"/>
    		<constructor-arg>
    			<list>
    				<value>3번 타자</value>
    				<value>유격수</value>
    			</list>
    		</constructor-arg>
    		<property name="height" value="188"/>
    		<property name="weight" value="80"/>
    	</bean>
    
    </beans>
    ```

    * java코드에 있는 설정 xml안에 포함

  * `MainBaseBall.java`

    ```java
    package com.test.diEx08;
    
    import org.springframework.context.support.AbstractApplicationContext;
    import org.springframework.context.support.GenericXmlApplicationContext;
    
    public class MainBaseball {
    	public static void main(String[] args) {
    		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:baseBall3.xml");
    		
    		Player player1 = ctx.getBean("player1", Player.class);
    		System.out.println("선수이름: "+player1.getName());
    		System.out.println("나이: "+player1.getAge());
    		System.out.println("포지션: "+player1.getPosition());
    		System.out.println("신장: "+player1.getHeight());
    		System.out.println("몸무게: "+player1.getWeight());
    		
    		System.out.println("===================================");
    		
    		Player player2 = ctx.getBean("player2", Player.class);
    		System.out.println("선수이름: "+player2.getName());
    		System.out.println("나이: "+player2.getAge());
    		System.out.println("포지션: "+player2.getPosition());
    		System.out.println("신장: "+player2.getHeight());
    		System.out.println("몸무게: "+player2.getWeight());
    		
    		ctx.close();
    	}
    }
    ```

### java코드에서 xml사용

* `com.test.diEx09`패키지 생성

  * `com.test.diEx08`패키지에서 `Player.java`, `ConfigApp.java`복사

  * `ConfigApp.java`

    ```java
    @Configuration
    @ImportResource("classpath:baseBall4.xml")
    public class ConfigApp {
        ...
    }
    ```

    * `@Configuration`추가

  * `baseBall4.xml`

    ```xml
    <bean id="player2" class="com.test.diEx09.Player">
        <constructor-arg value="강정호"/>
        <constructor-arg value="28"/>
        <constructor-arg>
            <list>
                <value>3번 타자</value>
                <value>유격수</value>
            </list>
        </constructor-arg>
        <property name="height" value="188"/>
        <property name="weight" value="80"/>
    </bean>
    ```

  * `com.test.diEx07`의 `MainBaseball.java`복사



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

