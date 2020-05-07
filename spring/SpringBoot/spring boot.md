# Spring Boot Admin

> https://www.baeldung.com/spring-boot-admin 를 참조하여 번역 및 정리한 문서입니다.

### Overview

* **Spring Boot Admin**은 Spring Boot 애플리케이션을 관리하고 모니터링하기 위해 사용되는 웹 애플리케이션 
* 각각의 애플리케이션은 클라이언트로 여겨지며, 관리자(admin) 서버에 등록된다
* 화면 뒤에서, Spring Boot Actuator endpoint에 의해 구현된다
* 이 글에서, Spring Boot Admin 서버를 설정하는 단계와 어떻게 애플리케이션이 client가 되는지에 대해 설명할 것이다.

### Admin Server Setup

* 우선, 간단한 Spring Boot 웹 애플리케이션을 만들고, 다음 dependency추가

  * dependency에 `Spring Web`추가

  * `Maven`인 경우

    ```maven
    <dependency>
        <groupId>de.codecentric</groupId>
        <artifactId>spring-boot-admin-starter-server</artifactId>
        <version>2.2.2</version>
    </dependency>
    ```

  * `Gradle`인 경우

    ```gradle
    implementation 'de.codecentric:spring-boot-admin-starter-server:2.2.2'
    ```

* 이제 `@EnableAdminServer`가 사용 가능하고, 아래와 같이 main클래스에 추가

  * `AdminServerApplication.java` 
  
  ```java
    package com.example.adminserver;
    
    import de.codecentric.boot.admin.server.config.EnableAdminServer;
    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;
    
    @EnableAdminServer
    @SpringBootApplication
    public class AdminServerApplication {
    
    	public static void main(String[] args) {
    		SpringApplication.run(AdminServerApplication.class, args);
    	}
    
    }
    ```
  
  * server와  client 애플리케이션을 등록할 준비가 됐다

### Setting Up a Client

* admin 서버를 셋팅한 후, client로 첫번째 Spring Boot 애플리케이션을 등록할 수 있다. 아래와 같이 dependecy를 추가한다

  * `Maven`인 경우

    ```maven
    <dependency>
        <groupId>de.codecentric</groupId>
        <artifactId>spring-boot-admin-starter-client</artifactId>
        <version>2.2.2</version>
    </dependency>
    <dependency>
    	<groupId>org.springframework.boot</groupId>
    	<artifactId>spring-boot-starter-data-rest</artifactId>
    </dependency>
    ```

  * `Gradle`인 경우

    ```gradle
    implementation 'de.codecentric:spring-boot-admin-starter-client:2.2.2'
    implementation 'org.springframework.boot:spring-boot-starter-data-rest'
    ```

* admin 서버의 URL을 client의 property에 설정

  * `application.properties`

    ```properties
    spring.boot.admin.client.url=http://localhost:8080
    ```

* 모든 endpoints를 client의 property에 설정

  * `application.properties`

    ```properties
    management.endpoints.web.expose.include=*
    management.endpoint.health.show-details=always
    ```

    * health와 info를 제외한 endpoints는 default로 노출(expose)되지 않음

### Security Configuration

* Spring Boot Admin 서버는 애플리케이션의 민감한(sensitive) endpoints에 접근한다

  * 따라서, admin과 client 애플리케이션 모두에게 security설정을 추가하도록 고안됨

* admin 서버에 security 설정하기. 다음과 같은 depenency 추가

  * `Maven`의 경우에

    ```maven
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
        <version>2.1.8.RELEASE</version>
    </dependency>
    ```

  * `Gradle`의 경우에

    ```gradle
    implementation 'org.springframework.boot:spring-boot-starter-security'
    ```

    * security는 이제 이용가능하며, admin 애플리케이션에 로그인 인터페이스를 추가할 것이다

* security 설정 클래스 추가

  * `WebSecuirtyConfig.java`

    ```java
    package com.example.adminserver;
    
    import de.codecentric.boot.admin.server.config.AdminServerProperties;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.http.HttpMethod;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
    import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
    import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
    import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
    
    import java.util.UUID;
    
    @Configuration
    public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        private final AdminServerProperties adminServer;
    
        public WebSecurityConfig(AdminServerProperties adminServer){
            this.adminServer = adminServer;
        }
    
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
            successHandler.setTargetUrlParameter("redirectTo");
            successHandler.setDefaultTargetUrl(this.adminServer.getContextPath()+"/");
    
            http
                    .authorizeRequests()
                        .antMatchers(this.adminServer.getContextPath()+"/assets/**").permitAll()
                        .antMatchers(this.adminServer.getContextPath()+"/login").permitAll()
                        .anyRequest().authenticated()
                        .and()
                    .formLogin()
                        .loginPage(this.adminServer.getContextPath()+"/login")
                        .successHandler(successHandler)
                        .and()
                    .logout()
                        .logoutUrl(this.adminServer.getContextPath()+"/logout")
                        .and()
                    .httpBasic()
                        .and()
                    .csrf()
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .ignoringRequestMatchers(
                                new AntPathRequestMatcher(this.adminServer.getContextPath()+"/instances", HttpMethod.POST.toString()),
                                new AntPathRequestMatcher(this.adminServer.getContextPath()+"/instances/*", HttpMethod.DELETE.toString()),
                                new AntPathRequestMatcher(this.adminServer.getContextPath()+"/actuator/**")
                        ).and()
                    .rememberMe()
                        .key(UUID.randomUUID().toString())
                        .tokenValiditySeconds(1209600);
        }
    }
    ```

    * 간단한 security를 설정할 수 있지만, 더 이상 클라이언트가 서버를 등록할 수 없다

* client에 새롭게 보안된 서버에 등록하기 위해, client의 property파일에 설정을 추가해야 한다

  * client의 `application.properties`

    ```properties
    spring.boot.admin.client.username=admin
    spring.boot.admin.client.password=admin
    
    server.port=8090
    ```

    * 운영 시스템에서, 모니터링하려는 애플리케이션은 보안적용이 되어야 한다
    * 클라이언트에도 security를 마찬가지로 추가할 것이고, admin server의 UI interface에서 client의 정보가 더이상 이용가능하지 않음을 알 수 있다

* admin server에 보낼 metadata를 추가해야 한다. 이 정보는 client의 endpoints에 연결하기 위해 서버에서 사용된다

  * admin server의 `application.properties`

    ```properties
    spring.security.user.name=admin
    spring.security.user.password=admin
    spring.boot.admin.client.instance.metadata.user.name=${spring.security.user.name}
    spring.boot.admin.client.instance.metadata.user.password=${spring.security.user.password}
    ```

    * HTTP를 통해 credential을 보내는 것은 안전하지 않다. 그러므로 커뮤니케이션은 HTTPS를 고려할 필요가 있다

### Monitoring and Management Features

* Spring Boot Admin은 유용하다고 여겨지는 정보만 보이도록 설정될 수 있다. 그러기 위해서는 default 설정을 변경하고, 필요한 metrics를 추가해야만 한다

  * `application.properties`

    ```properties
    spring.boot.admin.routes.endpoints = env, metrics, trace, jolokia, info, configprops
    ```

* *Jolokia*와 *Loglevel* management를 사용하여 JMX bean관리를 해보자

  * Spring Boot Admin은 Hazelcast를 사용하여 cluster 복제(replication)을 지원한다. 아래와 같이 dependency를 추가하면 자동 설정이 나머지를 하게 한다

    * `Maven`의 경우

      ```maven
      <dependency>
          <groupId>com.hazelcast</groupId>
          <artifactId>hazelcast</artifactId>
          <version>3.12.2</version>
      </dependency>
      ```

    * `Gradle`의 경우

      ```gradle
      implementation 'com.hazelcast:hazelcast'
      ```

  * Hazelcst의 영구적인 인스턴스(persistance of Hazelcast)를 원한다면, 커스텀한 설정을 사용해야 한다

    ```java
    package com.example.adminserver;
    
    import com.hazelcast.config.*;
    import com.hazelcast.map.merge.PutIfAbsentMapMergePolicy;
    import org.springframework.context.annotation.Configuration;
    
    import java.util.Collections;
    
    @Configuration
    public class HazelcastConfig {
    
        public Config hazelcast(){
            MapConfig eventStoreMap = new MapConfig("spring-boot-admin-event-store")
                    .setInMemoryFormat(InMemoryFormat.OBJECT)
                    .setBackupCount(1)
                    .setEvictionPolicy(EvictionPolicy.NONE)
                    .setMergePolicyConfig(new MergePolicyConfig(PutIfAbsentMapMergePolicy.class.getName(),100));
    
            MapConfig sentNotificationsMap = new MapConfig("spring-boot-admin-application-store")
                    .setInMemoryFormat(InMemoryFormat.OBJECT)
                    .setBackupCount(1)
                    .setEvictionPolicy(EvictionPolicy.LRU)
                    .setMergePolicyConfig(new MergePolicyConfig(PutIfAbsentMapMergePolicy.class.getName(),100));
            Config config = new Config();
            config.addMapConfig(eventStoreMap);
            config.addMapConfig(sentNotificationsMap);
            config.setProperty("hazelcast.jmx", "true");
    
            config.getNetworkConfig()
                    .getJoin()
                    .getMulticastConfig()
                    .setEnabled(false);
            TcpIpConfig tcpIpConfig = config.getNetworkConfig()
                    .getJoin()
                    .getTcpIpConfig();
            tcpIpConfig.setEnabled(true);
            tcpIpConfig.setMembers(Collections.singletonList("127.0.0.1"));
            return config;
        }
    }
    ```

### Notifications

* 등록된 client에 무슨일이 발생한다면 admin 서버로부터 알림(notification)을 받을 가능성이 있다. 다음 notifier들이 설정 가능하다
  * Email
  * PagerDuty
  * OpsGenie
  * Hipchat
  * Slack
  * Let's Chat

#### Email Notification

* admin 서버에 mail 설정하기

  * mail starter dependency 추가

    * `Maven`의 경우

      ```maven
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-mail</artifactId>
          <version>2.1.7.RELEASE</version>
      </dependency>
      ```

    * `Gradle`의 경우

      ```gradle
      implementation 'org.springframework.boot:spring-boot-starter-mail'
      ```

  * 메일 설정 추가

    * `application.properties`

      ```properties
      spring.mail.host=smtp.example.com
      spring.mail.username=smtp_user
      spring.mail.password=smtp_password
      spring.boot.admin.notify.mail.to=admin@example.com
      ```

  * 등록된 client가 상태가 UP에서 OFFLINE으로 바뀌는 등 변화가 있을 때마다, email이 위의 설정된 address로 보내진다

### references

> https://codecentric.github.io/spring-boot-admin/1.5.7/

