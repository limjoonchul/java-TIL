# 트랜잭션
* 어떤 논리적인 작업 단위, 데이터베이스의 상태를 바꾸는 기능을 수행하기 위한 작업의 단위

## 트랜잭션이 필요한 이유
* A라는 사람이 B라는 사람이게 돈을 보내려고 할 때, A라는 사람이 돈을 송금하면 A의 통장에서 돈이 빠져나가고
B라는 사람의 통장에 돈이 입금되는 이러한 작업이 하나의 트랜잭션이고 이 작업이 꼭 한번에 이루어져야한다.
A의 통장에는 돈이 빠져나갔지만, B의 통장에는 돈이 들어오지 않았다면 문제가 생기니깐
* 이 두개의 작업이 모두 이뤄지지 않았을 때, 이러한 작업이 이뤄지지 않은 상태 이전으로 돌려야 한다 이것이 롤백
두개의 작업이 모두 성공적으로 이뤄졌다면 커밋이다.

## 여러 트랜잭션이 이루어질 때 발생하는 문제점
1. 트랜잭션 A가 제목이라는 컬럼의 값을 가에서 나로 변경할 때 커밋되지 않은 상황에서 트랜잭션 B는 이 변경된 데이터를 읽은 경우
A가 다시 롤백 되면 B는 잘못된 값을 읽게 된 것이다.
아지 수정중인 상태에서 다른 트랜잭션이 접근해 이 값을 읽을 수 있어서 데이터 불일치가 발생할 수 있다.

2. 트랜잭션 A가 제목컬럼의 값을 한번 읽고 다시 값을 읽으려하는데 그 사이에 트랜잭션 B가 제목의 값을 가에서 나로변경하면?
두번 다 똑같은 쿼리리로 동일한 데이터를 읽었지만 다른 데이터들이 읽히게 된다.
한 트랜잭션에서 같은 쿼리를 두번 실행했을 때 데이터 불일치

3. 트랜잭션 A가 특정 범위의 값을 읽고 다시 같은 값 [2,4,6,8]을 읽고, 같은 쿼리로 값을 읽어들이려할 때
B가 [1,3,5,7]라는 값을 추가해 놓은 상황이였다면 A가 읽어들이는 값은 [1,2,3,4,5,6,7,8]로 변경된 값을
읽어들이게 될 것이다.
한 트랜잭션에 일정 범위의 레코드를 두번 이상 읽을 때 발생하는 데이터 불일치

## 선언적 트랜잭션
* 미리 선언된 룰에 따라 트랜잭션을 제어하는 방법
* 정해진 룰을 준수함으로써 트랜잭션 시작과 커밋, 롤백등의 일반적인 처리를 비즈니스로직안에 기술할 필요가 없다
 
* 애노테이션 밥벅과  xml 설정 방식이 있다.
## 트랜잭션의 속성

### 트랜잭션 격리수준
* Level 0
  * 다른 트랜잭션이 커밋하지 않은 레코드에 접근이 가능한 수준 성능은 가장 뛰어나다.

* Level 1
  * 커밋 되어 있는 레코드에만 접근이 가능하다. 대부분의 DBMS에서 기본적으로 채택하고 있는 방식

* Level 2
  * 한 트랜잭션이 읽은 레코드에 대해서 이 트랜잭션이 끝날 때까지 다른 트랜잭션이 수정,삭제하는 것을
  제한한다. 2번째 문제점을 예방할 수 있다.

* Level 3
  * 어떤 트랜잭션이 여러개의 레코드를 조회할 때, 이 트랜잭션이 끝날때까지 레코드의
  조회,입력, 수정, 삭제를 제한한다. 성능이 가장 안좋음

### propagation (전파속성)
*  트랜잭션 동작 도중 다른 트랜잭션을 호출하는 상황에서 선택할 수 있는 옵션
호출되는 트랜잭션의 경우 호출한 트랜잭션을 그대로 사용할 수있고, 새롭게 트랜잭션을 생성할 수 있다.
 
* REQUIRED - 디폴트 속성, 부모 트랜잭션 내에서 실행하여 부모 트랜잭션이 없을 경우 새로운 트랜잭션을 생성한다.
* SUPPORTS - 이미 시작된 트랜잭션이 있으면 참여하고 그렇지 않으면 트랜잭션 없이 진행하게 만든다.
* REQUIRES_NEW - 부모 트랜잭션을 무시하고 무조건 새로운 트랜잭션이 생성
* MANDATORY - REQUIRED와 비슷하게 이미 시작된 트랜잭션이 있으면 참여한다.
* NOT_SUPPORTED - 트랜잭션을 사용하지 않게 한다.
* NEVER - 트랜잭션을 사용하지 않도록 강제, 이미 진행중인 트랜잭션도 존재하면 안된다 있다면 예외 발생
* NESTED - 이미 진행중인 트랜잭션이 있으면 중첩 트랜잭션을 시작한다.

### readOnly 속성
* 트랜잭션을 읽기 전용으로 설정할 수 있다
* 성능을 최적화하기 위해 사용할 수도 있고 특정 트랜잭션 작업 안에서 쓰기 작업이 일어나는 것을 의도적으로 방지하기 위해 사용할 수 있다.
* 일부 트랜잭션 매니저의 경우 읽기전용 속성을 무시하고 쓰기 작업을 허용할 수도 있기 때문에 즈의 해야 한다.

### 트랜잭션 롤백 예외(rollback-for, rollbackForClassName)
* 선언적 트랜잭션에서는 런타임 예외가 발생하면 롤백한다.
* 반면에 예외가 전혀 발생하지 않거나 체크 예외가 발생하면 커밋한다.
   * 체크 예외를 커밋 대상으로 삼은 이유는 체크 예외가 예외적인 상황에서 사용되기 보다는 리턴 값을 대신해서 비즈니스적인
   의미를 담은 결과를 돌려주는 용도로 많이 사용되기 때문이다.
   
### timeout 속성
지정한 시간 내에 해당 메소드 수행이 완료되지 않은 경우 rollback 수행 `@Transactional(timeout=10)`


## Transaction Manager

![트랜잭션흐름](/Java/documents/images/트랜잭션.jpg)

```xml
<!-- TransactionManager 등록
TransactionManager는 데이터베이스 연동 기술에 따라 다른 클래스를 등록해야 한다.
예를 들어 Hibernate를 이용하여 DAO 클래스를 구현했다면 HibernateTransactionManager를 등록해야 한다.
그리고 모든 TransactionManager 클래스들은 FlatformTransactionManager 인터페이스를 구현하고 있다. 
-->
<bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="dataSource"></property>
</bean>
```
* 어떤 데이터베이스 연동 기술을 기반으로 하고 있느냐(DAO 클래스를 어떤 프레임워크나 어떤 기술로 구현했는지)에 따라서 TransactionManager 객체를 다르게
사용한다. 스프링 데이터베이스나, 마이바티스, 아이바티스 를 사용할 경우 DataSourceTranscationManager를 사용한다.
이게 가장 많이 사용된다. 

* 이러한 트랜잭션매니저 클래스들은 `FlatformTransactionManager` 인터페이스를 implements하고 있다.
이 안에 `Commit` 과 `Rollback` 메소드가 있다. 그래서 DB 연동에 따라 다른 트랜잭션매니저 클래스를 사용해야 하는 이유가
각 클래스마다 이 커밋과 롤백에 대한 알고리즘이 다르게 재정의 되어 있기 때문이다.

### 트랜잭션 Advice 클래스
* 트랜잭션에서 Adivice를 사용하기 위해서 tx 네임스페이스를 먼저 추가해줘야 한다.
* 트랜잭션 어드바이스는 개발자들이 직접 구현하지 않고 스프링 컨테이너가 자동으로 생성해주는 객체를 사용한다.
```xml
<tx:advice id="txAdvice" transaction-manager="txManager">
   <tx:attributes>
        <tx:method name="*" rollback-for="Exception"/>
   </tx:attributes>
</tx:advice>
```
* 위의 xml을 해석하면 이 tx Advice의 id는 txAdvice로 설정하고 트랜잭션 매니저의 id가 `txManager`인
객체를 참조하여서 모든 메소드(tx:method name="*")에서 예외가 발생하면 롤백(rollback-for="Exception")을 하라는 의미이다.

### pointcut 과 txAdvice를 연결 하기 위한 AOP 설정
* AOP를 할 때도 나온 내용이지만 핵심관심과 횡단관심을 잘 정의해놔도 Aspect를 설정해두지 않으면
아무 의미가 없다. 
* 조인포인트만으론 어드바이스가 동작하지 않기 때문에 포인트컷이 있어야 된다
* 포인트컷과 어드바이스를 연결하는 aspect가 필요하다.

```xml
 <aop:config>
   <aop:pointcut id="txPointcut" expression="execution(* com.rubypaper.biz..*Impl.*(..))"/>
   <aop:advisor pointcut-ref="txPointcut" advice-ref="txAdvice"/>
</aop:config>
```
* `txPointcut`를 참조해서 모든 Impl 클래스의 비즈니스 메소드에 대해서 표현식으로 필터링된 메소드를
txAdvise를 이용한 트랜잭션 매니저로 트랜잭션을 관리하는 것이다.
 
#### 주의! 
* 트랜잭션 AOP 설정에서는 Aspect 태그 대신 advisor 태그를 사용해야 한다.
  * 이유는 aspect는 method를 설정하는 속성을 넣어줘야한다. 우리가 만든 어드바이스 클래스를 사용할 때는 클래스안의 메소드명을 알기 때문에
  method를 설정할 수 있었지만, tx의 객체는 컨테이너에서 생성해주기 때문에 그 안의 메소드를 알 수 없기 때문에
  advisor 태그를 사용하는 것이다.
  

  
 


