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


#### 할인 정책 구성하기


---

### 04 상속과 다형성

#### 컴파일 시간 의존성과 실행 시간 의존성

#### 차이에 의한 프로그래밍

#### 상속과 인터페이스

#### 다형성

#### 인터페이스와 다형성


---

### 05 추상화와 유연성

#### 추상화의 힘

#### 유연한 설계

#### 추상 클래스와 인터페이스 트레이드오프

#### 코드 재사용

#### 상속

#### 합성
