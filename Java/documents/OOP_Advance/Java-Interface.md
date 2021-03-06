# 인터페이스(Interface)
2020-08-11 3-2차
## 인터페이스란
  * 클래스를 사용하는 방식, 접점만을 선언하는 클래스와 유사한 틀
  * 아무런 구현이 되어 있지 않으며, 모든 메소드가 추상 메소드
  * 이름 짓는 방법
      1. interface IFOO <- class Foo 상속을하는 자식에서 부모방향으로 / 앞에 i로씀
      2. interface Printable(형용사형태로 많이 씀) <- Bar  사용가능한 형용사형태로 씀

## 인터페이스의 특징
  * `class`가 아니고 `interface` 키워드를 사용한다.
     * `public` 또는 `default` 접근 제어자만 사용 가능.
  * 구현코드가 들어가지 않아서 new(인스턴스화)될 수 없다.
  * 생성자를 가질 수 없고, 객체 생성을 할 수 없음.
  * 자식 객체는 참조할 수 있으나 메소드와 변수는 사용 X
  * 인터페이스명으로 클래스 메소드 호출이 가능하나, 
  * 자식클래스명으로 클래스 메소드 호출 불가능.
  * 멤버 변수는 항상 `public static final` 이다. (생략 가능하다.)
  * 멤버 메소드는 항상 `public abstract` 이며, 생략가능
  * private abstract는 의미가 없다.
  * (자바에서는) 클래스는 하나만 상속할 수 있으나, 인터페이스는 여러개 가능
  * 인터페이스의 상속은 implements 키워드 사용
  
## 인터페이스의 요소
* 상수: 선언된 모든 변수는 상수로 처리됨(static 멤버변수는 가질 수 있지만, 인스턴스 멤버 변수는 가질 수 없다)
  * 인터페이스명.클래스 멤버 변수 접근이 가능하다.
* 메소드 : 모든 메소드는 추상 메소드
* 디폴트 메소드 : 기본 구현을 가지는 메소드 구현하는 클래스에서 재정의 할 수 있음(java 8)
* 정적 메소드 : 인스턴스 생성과 상관없이 인터페이스 타입으로 호출하는 메소드(java 8)
* private 메소드 : 인터페이스 내에서 사용하기 위해 구현한 메소드
구현하는 클래스에서 재정의 할 수 없음(java9)

 ````java
  interface IFoo{
      public static final int MEMBER_VAR = 10; //변하지 않는 상수는 대문자_ 형식으로 사용하기 때문에 여기도 사용한다.
      int MEMBER_VAR2 = 20 ; //이렇게 사용해도ㅗ됨 PUBLIC STATIC FINAL;
  
      public abstract void methodA(int param);
      void methodB(int param); //public abstract
  
  }
  
  class Foo implements IFoo{
  
      @Override
      public void methodA(int param) {
          System.out.println(param);
      }
  
      @Override
      public void methodB(int param) {
          System.out.println(param);
  
      }
  }
  ````
## 인터페이스간의 상속
  * 추상클래스도 추상클래스로 상속 가능했는데. 인터페이스도 인터페이스로 상속 가능 이 경우 extends 사용
  * 인터페이스에서 인터페이스로 상속하면 구현하는 것이아니라서 extends 사용
  * 클래스 <- 클래스 상속과 달리 다중 상속 가능
  ````java
  interface  Walkable{
      void walk();
  }
  interface  Runable{
      void run();
  }
  
  interface Jumpable extends  Walkable, Runable{
      void jump();
      //  워크 런이 다 포함되어있음
  }
  
  class Jumper implements Jumpable{
  // 3개다 구현해야함  -?
      @Override
      public void walk() {
          System.out.println("걷다");
      }
  
      @Override
      public void run() {
          System.out.println("달리다");
      }
  
      @Override
      public void jump() {
          System.out.println("뛰다");
  
      }
  }
  ````

## 인터페이스의 역할은?
* 인터페이스는 클라이언트 프로그램에 어떤 메소드를 제공하는지 알려주는 명세 또는 약속
* 한 객체가 어떤 인터페이스의 타입이라 함은 그 인터페이스의 메소드를 구현했다는 의미
* 클라이언트 프로그램은 실제 구현 내용을 몰라도 인터페이스의 정의만 알면 그 객체를 
사용할 수 있음
* 인터페이스를 구현해 놓은 다양한 객체를 사용함 - 다형성
   * JDBC를 구현한 오라클, MSSQL라이브러리 등
   
## 인터페이스와 strategy pattern
* 인터페이스를 활용하면 다양한 정책이나 알고리즘을 프로그램의 큰 수정 없이 적용, 확장할 수 있음

## JDK 1.8 이후의 인터페이스
  * 인터페이스에는 default 메소드를 구현할 수 있음.
  * default 메소드는 항상 public이다. 인터페이스는 원래 메소드를 구현할 수 없는데 이걸 깨지는게 default 다 abstract로 구현
  * 인터페이스 철학과 맞지 않으나, 기존에 사용하던 인터페이스가 개선되어, 모든 자식 메소드에 동일하게
  *   구현되어야 하는 메소드가 생긴 경우에 쉽게 기능을 추가하기 위해 만들어짐.
  *   많은 클래스들이 상속하는 경우 인터페이스에서 상속하는 경우 선택적으로 오버라이드해서 사용할 수 있음
   ````java
  interface IFoo2{
      void abstractMethod();
      default void defaultMethod(){ //디폴트 메소드
          System.out.println("default method");
      }
  }
  
  class FooTwo implements IFoo2{
      @Override
      public void abstractMethod() {
          System.out.println("abstract method implemented");
      }
  
  //    @Override // overriding not necessary 반드시 오버라이딩 해야 하는 것은 아님.
  //    public void defaultMethod() {
  //        System.out.println("overriden default method");
  //    }
  
  }
  //처음부터 디폴트메소드를 구현하고 시작하는 것은 철학적으로 맞지않음 원래 abstract 메소드만 있음
  
  class SuperFoo{ //인터페이스가 있는데 부족한 부분이있어서 메소드를 생성하서 사용하고 있었다
      public void defaultMethod(){
          System.out.println("default method in super class");
      }
  }
  
  
  class Foo3 extends SuperFoo implements IFoo2 {
  
      @Override
      public void abstractMethod() {
          System.out.println("abstract metho implemented");
      }
  
  }
  ````
 * 인터페이스의 static 메소드 : 클래스 메소드와 동일하게 사용 가능
   * 인터페이스 이름으로 호출 가능
   * 클래스 구현체의 이름으로도 호출 가능
  ````java
   interface IFoo {
       static void staticMethod() {
           System.out.println("static method");
       }
   }
   
   public class Main {
       public static void main(String[] args) {
           IFoo.staticMethod(); // static method
       }
   }
  ````