#Java 2-3
# 제어자(Modifier)
## 제어자란
* 클래스, 변수, 메소드에 부가 기능을 부여하는 키워드
* 접근 제어자 (Access modifiers)
   * 접근할 수 있는 범위를 정하는 키워드
   * 자바 파일명과 같은 public class 클래스명이 꼭 있어야 한다.
   * private – 같은 클래스에서만 사용가능, 내부 구현을 위해서만 사용한다.
   * default – package라고도 한다 제어자명은 생략한다. 사용범위가 같은 패키지안에서 가능.
   * protected – private와 비슷하게 사용이 되나, 상속한 경우 구현 시 접근이 필요할 때 사용.
               자식이면 다른 패키지여도 접근할 수가 있다, 하나의 자식외에는 못본다, 부모의 부모는 건들 수 없다.
   * public – 모든 곳에서 사용가능.
   * 클래스는 public 과 default만 사용 가능하다.
   
* 그 외 제어자 (Other modifiers)
   * 특별한 기능을 부여하는 제어자
   * static, final, abstract, synchronized
### 접근 제어자
````java
package com.company.s04.p05.subp01;
public class ClassA {
    public  int x;
    protected  int y;
    int z; // default (=package)
    private int w;

    public void methodA(){} //
    protected void methodB(){} //
    void methodC(){}//default (=package)
    private void methodD(){} //private - 내부 구현을 위해서만 사용.

    public void methodTest(){ //같은 클래스
        System.out.println(x);
        System.out.println(y);
        System.out.println(z);
        System.out.println(w);

        methodA();
        methodB();
        methodC();
        methodD();
    }
}

class ClassATest{ // 같은 패키지인 경우.
    public static void main(String[] args) {
        ClassA obj = new ClassA();
        System.out.println(obj.x);
        System.out.println(obj.y);
        System.out.println(obj.z);
        //System.out.println(obj.w); 접근 불가

        obj.methodA();
        obj.methodB();
        obj.methodC();
        //obj.methodD(); private 검색도 안됨
    }
}
````
* 다른 패키지
````java
package com.company.s04.p05.subp02;
import com.company.s04.p05.subp01.ClassA;

class ClassAA extends ClassA{
    public void methodTest(){
        System.out.println(x);
        System.out.println(y); //protected 자식이면 다른 패키지여도 ok 실제로 많이 씀. 하나의 자식 외에는 못봄. 부모의 부모는 건들 수 없음.(자바특성)
        // protected는 private처럼 쓰지만, 상속한 경우 구현 시 접근이 필요 할 때 사용.
       // System.out.println(z);
       // System.out.println(w); private - 상속해도 못봄

        methodA();//public
        methodB();//protected
        //methodC();
        //methodD();
    }
}

// public default만 사용 가능, private은 쓸 수 없음 같은 클래스만 볼 수 있기 때문에 다른 클래스라는 개념이 없기 때문에. protected도 사용 불가.
public class ClassB {
    public static void main(String[] args) {
        ClassA obj = new ClassA();
        System.out.println(obj.x); //다됨 public
       // System.out.println(obj.y); //protected 다른 패키지인경우 자식만됨.
        //System.out.println(obj.z); //default 다른 패키지이면 안됨.
        //System.out.println(obj.w); 접근 불가

        obj.methodA();
        //obj.methodB();//protected 다른 패키지인경우 자식만됨.
        //obj.methodC();//default 다른 패키지이면 안됨.
        //obj.methodD(); private 검색도 안됨
    }
}
```` 
### public으로 open한거랑 private으로 닫아놓고 public method를 제공하는것의 차이
* 2가지 차이점이 있다
  1. getter를 할 수 있게하고, setter를 못하게(안하게) 하는 경우가 있다 (ReadOnly만 된다, 값을 넣을 수 없다, ID라던가 값이 변할 수 없는 고유한 값들)
  2. 메소드에서 유효한 데이터를 핸들링 할 수 있다.
     * boolean isValid 라는 멤버변수를 선언해주고 이것이 true일 때 올바른 날짜가 출력되게 하므로써 제한사항을 줄 수 있다
     * 멤버변수이기 때문에 클래스 전체에서 사용이 가능하므로 (scope,범위가) 메소드에서 값을 변경하게 되면 멤버변수의 값이 변경되기 때문에
 ```groovy
     public class MyDate {
         private int day;
         private int month;
         private int year;
     
         private boolean isVaild = true;
     
         public  void setDay(int day){
             this.day = day;
         }
         public int getDay(){
             return day;
         }
     
         public int getMonth() {
             return month;
         }
     
         public void setMonth(int month) {
             if(month <1 || month > 12){
                 isVaild = false; // 값이 유효범위에 해당하는지 체크를 해준다.
             }
             this.month = month;
         }
     
         public int getYear() {
             return year;
         }
     
         public void setYear(int year) {
             this.year = year;
         }
     
         public void showDate(){
             if (isVaild){
                 System.out.println(this.year + "년" + this.month + "월" +this.day+"일");
             }else{
                 System.out.println("유효하지 않은 범위 입니다.");
             }
     
         }
     
     
     }
 ```

### 그 외의 제어자
  * final 
  * - 더 이상 바꿀 수 없음을 의미 상수.
  * - 클래스, 메소드, 변수에 사용 가능
    * 클래스 : 더 이상 상속이 불가능해짐.
    * 메소드 : 자식 클래스에서 오버라이드 할 수 없다.
    * 변수 : 변수의 값이 초기화 이후에 변하지 않는다.
    * 생성자에서 초기화가 이루어지는 것을 blank final 변수라 한다.
   ````groovy
    public class Foo {
        final int x = 0; // final variable
        final int y; // blank finial variable
    
        public Foo(int y) {
          this.y = y; // blank final variable initialization
      }
    }
   ````
  * static
    * 클래스 변수, 클래스 메소드 등을 선언하기 위해 사용
  * abstract
    * 추상 클래스에서 사용.
  * synchronized
    * 동시성 프로그래밍에 사용.

 
# 싱글톤 패턴(Singletone)
 * 객체가 단 하나만 존재할 수 있는 클래스
 * private 생성자를 이용한다.
 
````java
class SingletonClass {
  private static SingletoneClass instance = new SingletonClass();
  private SingletonClass() {}

  public static SingletonClass getInstance() {
      return instance;
  }
}
````

