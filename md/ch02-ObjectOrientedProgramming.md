# Chapter02 객체지향 프로그래밍

### 01 영화 예매 시스템

#### 요구사항 살펴보기
- 온라인 영화 예매 시스템
- '영화'와 '상영'이라는 용어를 구분
    - 영화: 제목, 사영시간, 가격 정보와 같이 영화가 가지고 있는 기본적인 정보
    - 상영: 실제로 관객들이 영화를 관람하는 사건
- 특정한 조건을 만족하는 예매자는 요금 할인 가능
    - 할인 조건(discount condition)
        - 가격의 할인 여부를 결정
        - 종류
            - 순서 조건(sequence condition): 상영 순번을 이용해 할인 여부를 결정
            - 기간 조건(period condition): 상영 시작 시간을 이용해 할인 여부를 결정
        - 다수의 할인 조건을 함께 지정 가능
    - 할인 정책(discount policy)
        - 할인 요금을 결정
        - 종류
            - 금액 할인 정책(amount discount policy): 일정 금액 할인
            - 비율 할인 정책(percent discount policy): 일정 비율의 요금을 할인
        - 영화별로 하나의 할인 정책만 할당
        - 정책을 지정하지 않는 것도 가능


---

### 02 객체지향 프로그래밍을 향해

#### 협력, 객체, 클래스
- 객체지향은 객체를 지향하는 것
- 객체지향 프로그램을 작성할 때 가장 먼저 고려하는 것은?
    - AS-IS: 어떤 클래스(class)가 필요한 고민
    - TO-BE: 클래스가 아닌 객체에 초점
        1. 어떤 객체들이 필요한지 고민, 어떤 객체들이 어떤 상태와 행동을 가지는지를 먼저 결정
        2. 이후 공통된 특성과 상태를 가진 객체들을 타입으로 분류하고 이 타입을 기반으로 클래스를 구현, 협력하는 공동체의 일원으로 바라보기

<br/>

#### 도메인 구조를 따르는 프로그램 구조
> 도메인(domain): 문제를 해결하기 위해 사용자가 프로그램을 사용하는 분야
- 요구사항과 프로그램을 객체라는 동일한 관점에서 바라볼 수 있음
- 클래스의 이름은 대응되는 도메인 개념의 이름과 동일하거나 적어도 유사하게 지어야 함
- 클래스 사이의 관계도 최대한 도메인 개념 사이에 맺어진 관계와 유사하게 만들어서 프로그램의 구조를 이해하고 예상하기 쉽게 만들어야 함

<br/>

#### 클래스 구현하기
```java
public class Screening {
    private Movie movie;
    private int sequence;
    private LocalDateTime whenScreened;

    public Screening(Movie movie, int sequence, LocalDateTime whenScreened) {
        this.movie = movie;
        this.sequence = sequence;
        this.whenScreened = whenScreened;
    }

    public LocalDateTime getStartTime() {
        return whenScreened;
    }

    public boolean isSequence(int sequence) {
        return this.sequence == sequence;
    }

    public Money getMovieFee() {
        return movie.getFee();
    }
}
```
- 인스턴스 변수의 가시성은 private이고 메서드의 가시성은 public으로 클래스의 경계를 구분지음.
- 클래스는 내부와 외부로 구분되며 훌륭한 클래스를 설계하기 위한 핵심은 어떤 부분을 외부에 공개하고 어떤 부분을 감출지 결정하는 것
- 구분해야 하는 이유?
    - 경계의 명확성이 객체의 자율성 보장
    - 프로그래머에게 구현의 자유 제공

<br/>

#### 자율적인 객체
> 1. 객체는 상태(state)와 행동(behavior)을 함께 가지는 복합적인 존재
> 2. 객체는 스스로 판단하고 행동하는 자율적인 존재
> - 객체: 객체라는 단위 안에 데이터와 기능을 한 덩어리로 묶음
> - 캡슐화: 데이터와 기능을 객체 내부로 함께 묶는 것
> - 접근제어(access control): 외부에서의 접근을 통제할 수 있는 매커니즘

- 객체 내부에 대한 접근을 통제하는 이유는 객체를 자율적인 존재로 만들기 위함
- 접근 통제로 외부의 간섭을 최소화 하여 자율적인 존재로 만듬
- 캡슐화와 접근 제어
    - 퍼블릭 인터페이스(public interface): 외부에서 접근 가능한 부분
        - public으로 지정된 메서드
    - 구현(implementation): 오직 내부에서만 접근 가능한 부분
        - private, protected 메서드 / 속성
    - 객체의 상태는 숨기고 행동만 외부에 공개해야 함

<br/>

#### 프로그래머의 자유
- 프로그래머의 역할
    1. 클래스 작성자(class creator)
        - 새로운 데이터 타입을 프로그램에 추가
        - 클라이언트 프로그래머에게 필요한 부분만 공개, 나머지는 숨김
        - 숨겨 놓은 부분에 마음대로 접근할 수 없도록 방지
        - 구현 은닉(implementation hiding): 내부 구현을 마음대로 구현
    2. 클라이언트 프로그래머(client programmer)
        - 클래스 작성자가 추가한 데이터 타입을 사용
        - 필요한 클래스들을 엮어서 애플리케이션을 빠르고 안정적으로 구축
- 접근 제어 메커니즘
    - 내부와 외부를 명확하게 경계 지음
    - 클래스 작성자가 내부 구현을 은닉
    - 실수로 숨겨진 부분에 접근하는 것을 방지
- 구현 은닉
    - 클라이언트 프로그래머는 인터페이스로만 구현(내부 구현을 알필요 없음)
    - 클래스 작성자는 인터페이스 변경이 없는 한 내부 구현 자유로움    
- 즉 클래스를 개발할 때마다 인터페이스와 구현을 깔끔하게 분리하기위해 노력필요

<br/>

#### 협력하는 개체들의 공동체
- [Screening](../ObjectsExampleCode/src/main/java/com/wooklab/example/chapter02/Screening.java)
- [Money](../ObjectsExampleCode/src/main/java/com/wooklab/example/chapter02/Money.java)
- [Reservation](../ObjectsExampleCode/src/main/java/com/wooklab/example/chapter02/Reservation.java)
> 영화를 예매하기 위해 Screening, Movie, Reservation 인스턴스들은 서로의 메서드를 호출하며 상호작용함. <br/>
> <sup>*</sup>협력(Collaboration): 시스템의 어떤 기능을 구현하기 위해 객체들 사이에 이뤄지는 상호작용

<br/>

#### 협력에 관한 짧은 이야기

- 메서드 Vs. 메시지
    - 객체간에 상호작용할 수 있는 유일한 방법은 메시지 전송(send a message)
        - 다른 객체에게 요청이 도착할 때 해당 객체가 메시지를 수신(receive a message)
    - 메서드(metohd): 수신된 메시지를 처리하기 위한 자신만의 방법


---

### 03 할인 요금 구하기

#### 할인 요금 계산을 위한 협력 시작하기

- [Movie](../ObjectsExampleCode/src/main/java/com/wooklab/example/chapter02/Movie.java)
> 어떤 할인 정책을 사용할 것이닞 결정하는 코드가 어디에도 존재하지 않음<br/>
> 단지 discountPolicy에게 메시지를 전송할 뿐
- 객체지향의 두 개념
    - 상속(inheritance)
    - 다형성
    - 그리고 그 기반에는 추상화(abstraction)라는 원리

<br/>

#### 할인 정책과 할인 조건

> 할인 정책은 '금액 할인 정책'과 '비율 할인 정책'으로 구분<br/>
> 두 클래스는 대부분의 코드가 유사, 할인 요금을 계산하는 방식만 다름

- 부모 클래스인 DiscountPolicy안에 중복코드를 두고 
- AmountDiscountPolicy와 PercentDiscountPolicy가 부모 클래스를 상속
- DiscountPolicy의 인스턴스를 생성할 필요가 없기 때문에 추상화 클래스(abstract class)로 구현
    -  [DiscountPolicy](../ObjectsExampleCode/src/main/java/com/wooklab/example/chapter02/discount/DiscountPolicy.java)
    -  [AmountDiscountPolicy](../ObjectsExampleCode/src/main/java/com/wooklab/example/chapter02/discount/impl/AmountDiscountPolicy.java)
    -  [PercentDiscountPolicy](../ObjectsExampleCode/src/main/java/com/wooklab/example/chapter02/discount/impl/PercentDiscountPolicy.java)

> 이와 같이 부모 클래스에 기본적인 알고리즘을 구현하고 중간에 필요한 처리를 자식 클래스에게 위임하는 디자인 패턴을 Template Method 패턴이라 함

- 오버라이딩(overriding) Vs. 오버로딩(overloading)
    - 오버라이딩
        - 부모 클래스에 정의된 같은 이름, 같은 파라미터 목록을 가진 메서드를 자식 클래스에서 재정의
        - 외부에서는 부모 클래스의 메서드가 보이지 않음
    - 오버로딩
        - 메서드의 이름은 같지만 제공되는 파라미터 목록이 다름
        - 부모와 자식 클래스의 메소드가 공존

<br/>

#### 할인 정책 구성하기

> 하나의 영화에 대해 <br/>
> 단 하나의 할인 정책만 설정할 수 있지만 <br/>
> 할인 조건의 경우에는 여러 개를 적용할 수 있다.

- 오직 하나의 DiscountPolicy 인스턴스만 받을 수 있도록 선언
    ```java
    public class Movie {
        public Movie(String title, Duration runningTime, Money fee, DiscountPolicy discountPolicy) {
        // ...
        this.discountPolicy = discountPolicy;
        }
    }
    ```
- DiscountPolicy의 생성자는 여러 개의 DiscountCondition 인스턴스를 허용
    ```java
    public abstract class DiscountPolicy {
        public DiscountPolicy(DiscountCondition ... conditions) {
            this.conditions = conditions;
        }
    }
    ```
=> 생성자의 파라미터 목록을 이용해 **초기화에 필요한 정보를 전달하도록 강제**하면 올바른 상태를 가진 객체의 생성을 보장


---

### 04 상속과 다형성

#### 컴파일 시간 의존성과 실행 시간 의존성

- [Movie](../ObjectsExampleCode/src/main/java/com/wooklab/example/chapter02/Movie.java)
- [DiscountPolicy](../ObjectsExampleCode/src/main/java/com/wooklab/example/chapter02/discount/DiscountPolicy.java)
- [AmountDiscountPolicy](../ObjectsExampleCode/src/main/java/com/wooklab/example/chapter02/discount/impl/AmountDiscountPolicy.java)
- [PercentDiscountPolicy](../ObjectsExampleCode/src/main/java/com/wooklab/example/chapter02/discount/impl/PercentDiscountPolicy.java)
> 코드 수준에서는 Movie 클래스가 추상 클래스인 DiscountPolicy에만 의존 <br/>
> 그러나 실행시점에는 AmountDiscountPolicy나 PercentDiscountPolicy의 인스턴스에 의존 <br/>
> => 즉 코드의 의존성과 실행 시점의 의존성이 서로 다를 수 있다.
- 코드의 의존성과 실행 시점의 의존성이 서로 다르면
    - 장점: 코드는 더 유연해지고 확장이 가능
    - 단점: 코드를 이해하기 어려워짐
- 의존성의 양면성은 설계가 트레이드오프의 산물
    - 훌륭한 객체지향 설계자로 성장하기 위해서는 항상 유연성과 가독성 사이에서 고민해야 하며
    - 무조건 유연한 설계도, 무조건 읽기 쉬운 코드도 정답이 아니다.

<br/>

#### 차이에 의한 프로그래밍

> 클래스를 하나 추가하고 싶은데 그 클래스가 기존의 어떤 클래스와 매우 흡사하다고 가정

- 상속
    - 클래스의 코드를 전혀 수정하지 않고도 재사용하는 것
    - 클래스 사이에 관계를 설정하는 것만으로 기존 클래스가 가지고 있는 모든 속성과 행동을 새로운 클래스에 포함
    - 기존 클래스를 기반으로 새로운 클래스를 쉽고 빠르게 추가할 수 잇는 간편한 방법을 제공
    - 부모 클래스의 구현은 공유하면서도 행동이 다른 자식 클래스를 쉽게 추가

<br/>

#### 상속과 인터페이스

- 인터페이스: 객체가 이해할 수 있는 메시지의 목록을 정의
- 상속
    - 자식 클래스는 자신의 인터페이스에 부모 클래스의 인터페이스를 포함
    - 부모 클래스가 수신할 수 있는 모든 메시지 수신 가능
    - 때문에 외부 객체는 자식 클래스를 부모 클래스와 동일한 타입으로 간주
- 즉 자식 클래스는 상속을 통해 부모 클래스의 인터페이스를 물려받기 때문에 부모 클래스 대신 사용 가능
- 업캐스팅(upcasting): 자식 클래스가 부모 클래스를 대신하는 것

<br/>

#### 다형성

- 다형성이란?
    - 객체지향 프로그램의 컴파일 시간 의존성과 실행 시간 의존성이 다를 수 있다는 사실 기반
    - 컴파일 시간 의존성과 실행 시간 의존성을 다르게 만들수 있는 객체지향의 특성을 이용해 서로 다른 메서드를 실행
    - 동일한 메시지를 수신했을 때 객체의 타입에 따라 다르게 응답할 수 있는 능력
- 다형성을 구현
    - 메시지에 응답하기 위해 실행될 메서드를 컴파일 시점이 아닌 실행 시점에 결정
    - 즉 지연 바인딩(lazy binding) 또는 동적 바인딩(dynamic binding)
- 다형성과 반대(전통적인 함수 호출)
    - 반대로 컴파일 시점에 실행될 함수나 프로시저를 결정하는 것
    - 초기 바인딩(early binding) 또는 정적 바인딩(static binding)
- 상속은 구현 상속이 아니라 인터페이스 상속을 사용
    - 구현 상속(implementation inheritance)
        - 서브클래싱(subclassing)
        - 코드를 재사용하기 위한 목적
    - 인터페이스 상속(interface inheritance)
        - 서브타이핑(subtyping)
        - 다형적인 협력을 위해 부모 클래스와 자식 클래스가 인터페이스를 공유

<br/>

#### 인터페이스와 다형성
- 자바의 인터페이스: 구현에 대한 고려 없이 다형적인 협력에 참여하는 클래스들이 공유 가능한 외부 인터페이스를 정의한 것
- 인터페이스를 실체화 하는 클래스들은 동일한 인터페이스를 공유


---

### 05 추상화와 유연성

#### 추상화의 힘

- 추상 클래스(abstract class) Vs. 자바 인터페이스(interface)
    - 공통점: 클래스들이 공통으로 가질 수 있는 인터페이스를 정의
    - 차이점
        - 추상 클래스: 구현의 일부를 자식 클래스가 결정
        - 자바 인터페이스: 구현의 전체를 자식 클래스가 결정
- 추상화 장점
    - 추상화 계층만 따로 떼어 놓고 살펴보면 요구사항의 정책을 높은 수준에서 서술 가능
    - 유연한 설계

<br/>

#### 유연한 설계

- 항상 예외 케이스를 최소화하고 일관성을 유지할 수 있는 방법을 선택
- 추상화를 중심으로 코드의 구조를 설계 -> 유연하고 확장 가능
- 추상화 -> 특정 조건에 묶이지 않고 어떤 클래스와도 협력 가능
- 즉 유연성이 필요한 곳에 추상화를 사용하라

<br/>

#### 추상 클래스와 인터페이스 트레이드오프

> 자식 클래스인 NonDiscountPolicy 객체의(할인 조건이 없을) 경우, 부모 클래스인 DiscountPolicy의 getDiscountAmount() 메서드를 호출하지 않는 경우 발생
- DiscountPolicy를 (기존 추상 클래스에서) 인터페이스로 바꾸고
- getDiscountAmount() 메서드가 아닌 calculateDiscountAmount() 오퍼레이션을 오버라이딩 하도록 변경
    - DiscountPolicy: 추상 클래스 -> 자바 인터페이스
    - 인터페이스 메서드의 변경: getDiscountAmount() -> calculateDiscountAmount()
    - DefaultDiscountPolicy 추상 클래스를 추가: calculateDiscountAmount() 구현
    - 기존 정책 클래스: getDiscountAmount() 구현

<br/>

#### 코드 재사용

- 코드 재사용을 위해 상속보다는 합성(composition)
- 합성은 다른 객체의 인스턴스를 자신의 인스턴스 변수로 포함해서 재사용 하는 방법

<br/>

#### 상속

- 상속은 캡슐화를 위반한다
    - 부모 클래스의 구현이 자식 클래스에게 노출되기 때문에 캐슐화 약화
    - 자식 클래스가 부모 클래스에 강하게 결합되도록 만들기 때문에 상속을 과도하게 사용한 코드는 변경이 어려움
- 상속은 설계가 유연하지 않음
    - 부모 클래스와 자식 클래스 사이의 관계를 컴파일 시점에 결정
    - 실행 시점에 객체의 종류를 변경하는 것이 불가능

<br/>

#### 합성

> 인터페이스에 정의된 메시지를 통해서만 코드를 재사용하는 방법

- 객체지향에서 가장 중요한 것
    - 애플리케이션의 기능을 구현하기 위해 협력에 참여하는 객체들 사이의 상호작용
    - 객체들은 협력에 참여하기 위해 역할을 부여받고 역할에 적합한 책임을 수행
    - 적절한 협력을 식별하고 협력에 필요한 역할을 정의한 후에 역할을 수행할 수 있는 적절한 객체에게 적절한 책임을 할당하는 것
