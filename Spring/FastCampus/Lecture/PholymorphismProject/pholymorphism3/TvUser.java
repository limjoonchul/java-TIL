package com.company.pholymorphism3;

// 다형성 = 상속 + 메소드 Overriding + 형변환(절차지향언어에서 지원하지 않음) 유지보수가 편함! 원 인터페이스 멀티플 인플리멘테이션
// 자식객체가 부모타입으로 되는걸 묵시적 형변환

public class TvUser {
    public static void main(String[] args) {
        BeanFactory factory = new BeanFactory();
        Tv tv = (Tv) factory.getBean(args[0]);
        tv.powerOn();
        tv.volumeUp();
        tv.volumeDown();
        tv.powerOff();
        // 이건 클라이언트에게 리모콘을 줘서 tv를 작동하라고 하는 것과 같다.
        // tv는 바껴도 리모콘은 안바뀌니깐 하나의 리모콘으로 여러개의 tv를 작동시킬 수 있다 이것이 다형성.
        // 클라이언트를 안건드리고 tv를 바꿀 수 있을까. --> 디자인 패턴을 적용하면 된다.

        // run config -> program confug 에 lg, samsung 을 넣으면 그 객체가 실행이됨.

        // 프로그램 개발 발전은 복사/붙여넣기로 시작이 되었다. 반복되는 코드들을 복사붙여넣기하니깐 너무많아 유지보수가 어려워서 생긴게
        // 함수가 생김 함수를 기반으로 절차지향언어 c가 생김 반복적으로 사용하는 코드를 함수로 모듈화 시켜놓으면 그 코드가 필요하면 함수만 호출하면되니깐
        // 반복되는 코드를 줄여나가게 되었고. 알고리즘만 재사용하니깐 재사용성이 좋아지면 좋겠다 해서 나온게 클래스이다.
        // 클래스는 단순히 함수만 있는게 아니라 함수가 동작할 때 필요한 변수까지 가지고 있어서 재사용성이 점점 좋아지는 쪽으로 발전함.

        // 클래스를 통째로 바꿔치기하면서 재사용을 하려면 다형성이라는게 필요하다! 그래서 객체지향언어의 핵심은 다형성이다.
        // 다형성을사용하지않을꺼면 복잡한 객체지향언어를 사용할 필요가 없다.

        // 다형성을사용했더니 클라이언트가 소스수정이 필요하다 그래서 이 다형성을 한단계 뛰어넘는게 디자인패턴인다.
        // 팩토리패턴을 사용하면 공장으로붙어 원하는 것을 얻어낸다. 클라이언트는 유지보수과정에서 수정되지 않는다.
        // 클라이언트(main)만 봐서는 무슨 tv가 생성되는지 알수 없다. 클라이언트는 이렇게 작성되어야 한다.

        // 구글 티비를 만들어서 그냥 프로그램 컨피규레이션에 google을 넣는다고 동작하지않음 빈팩토리에 분기를 만들어서 조건을 걸어줘야 동작이 된다.
        // 소스르수정하면 컴파일다시해야하고 컴파일다시하면 서버에 다시 올려야한다. 소스수정을 최소화시키는 방향으로 생각해야한다.
        // 다형성, 디자인패턴를 써도 해결되지 않은 문제를 해결하는 것이 스프링프레임워크이다. *핵심은 유지보수할 때 자바소스를 안건드리는 것이다. 그래서 컴파일을 다시 할 필요가 없다.


    }
}