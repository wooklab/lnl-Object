# 04 설계 품질과 트레이드오프

- 책임 주도 설계라는 이름에서 알 수 있듯이 역할, 책임, 협력 중에서 가장 중요한 것은 `책임` 이다
- 책임이 객체지향 애플리케이션 전체의 `품질`을 결정한다
- 객체지향 설계란 올바른 객체에게 올바른 책임을 할당하면서 낮은 결합도와 높은 응집도를 가진 구조를 창조하는 활동이다
    - 객체지향 설계의 두 관점
        1. 객체지향 설계의 핵심이 책임
        2. 책임을 할당하는 작업이 응집도와 결합도 같은 설계 품질과 깊이 연관
- 훌륭한 설계란 합리적인 비용 안에서 변경을 수용할 수 있는 구조를 만드는 것이다
- 그러기 위해선 객체의 상태가 아니라 객체의 행동에 초점 맞춰야 한다

<br/>

### 01 데이터 중심의 영화 예매 시스템
- 객체지향 설계에서 시스템을 객체로 분리하는 두 가지
    1. `상태`를 분할의 중심축으로 삼는 방법 (상태 = 데이터)
        - 객체는 자신이 포함하고 있는 데이터를 조작하는 데 필요한 오퍼레이션을 정의
        - 객체의 상태에 초점을 맞춤
        - 객체를 독립된 데이터 덩어리로 바라본다
    2. `책임`을 분할의 중심축으로 삼는 방법
        - 객체는 다른 객체가 요청할 수 있는 오퍼레이션을 위해 필요한 상태를 보관
        - 객체의 행동에 초점
        - 객체를 협력하는 공동체의 일원으로 본다
- 훌륭한 객체지향 설계는 데이터가 아니라 책임에 초점을 맞추는데 그 이유는 `변경`과 관련이 있다
- 상태 중심
    - 객체의 상태는 구현에 속한다. 구현은 불안정하기 때문에 변하기 쉽다.
    - 상태를 객체 분할의 중심축으로 삼으면 구현에 관한 세부사항이 객체의 인터페이스에 스며들게 되어<br/>
    -> 캡슐화의 원칙이 무너진다.
    - 결과적으로 상태 변경은<br/>
    -> 인터페이스의 변경을 초래하며<br/>
    -> 의존하는 모든 객체의 변경에 영향이 퍼지게 된다
- 책임 중심
    - 책임은 인터페이스에 속한다
    - 책임을 수행하는 데 필요한 상태를 캡슐화
    - 구현 변경에 대한 파장이 외부로 퍼져나가는 것을 방지
    - 상대적으로 변경에 안정적인 설계

#### 데이터를 준비하자 
- 데이터 중심의 설계란 객체 내부에 저장되는 데이터를 기반으로 시스템을 분할하는 방법이다
- 데이터 중심의 설계는 객체 내부에 저장해야 하는 '데이터가 무엇인가'를 묻는 것으로 시작한다

AS-IS : 책임중심
```java
public class Movie {
    private String title;
    private Duration runningTime;
    private Money fee;
    private DiscountPolicy discountPolicy;
}
```

TO-BE: 데이터(상태) 중심
```java
public class Movie {
    private String title;
    private Duration runningTime;
    private Money fee;
    private List<DiscountCondition> discountConditions;
    
    private MovieType movieType;
    private Money discountAmount;
    private double discountPercent;
}
```
- 가장 두드러지는 차이점
    - 할인 조건의 목록(discountConditions)이 인스턴스 변수로 Movie 안에 직접 포함
    - 할인 정책을 DiscountPolicy라는 별도의 클래스로 분리하지 않고, Movie 안에서 할인 금액(discountAmount)과 할인 비율(discountPercent)를 직접 정의
- 데이터 중심의 설계에서는 객체가 포함해야 하는 데이터에 집중한다
- Movie 클래스의 경우처럼 객체의 종류를 저장하는 인스턴스 변수(movieType)와 인스턴스 종류에 따라 배타적으로 사용될 인스턴스 변수(discountAmount, discountPercent)를 하나의 클래스 안에 포함시키는 방식은<br/>
-> 데이터 중심 설계의 흔한 패턴
- 객체지향의 가장 중요한 원칙인 캡슐화를 위해 내부의 데이터를 반환하는 접근자(accessor)와 데이터를 변경하는 수정자(mutator)를 추가  

<br/>

#### 영화를 예매하자
- 전체 소스코드
    -  [Customer](../ObjectsExampleCode/src/main/java/com/wooklab/example/chapter04/Customer.java)
    -  [DiscountCondition](../ObjectsExampleCode/src/main/java/com/wooklab/example/chapter04/DiscountCondition.java)
    -  [DiscountConditionType](../ObjectsExampleCode/src/main/java/com/wooklab/example/chapter04/DiscountConditionType.java)
    -  [Money](../ObjectsExampleCode/src/main/java/com/wooklab/example/chapter04/Money.java)
    -  [Movie](../ObjectsExampleCode/src/main/java/com/wooklab/example/chapter04/Movie.java)
    -  [MovieType](../ObjectsExampleCode/src/main/java/com/wooklab/example/chapter04/MovieType.java)
    -  [Reservation](../ObjectsExampleCode/src/main/java/com/wooklab/example/chapter04/Reservation.java)
    -  [ReservationAgency](../ObjectsExampleCode/src/main/java/com/wooklab/example/chapter04/ReservationAgency.java)
    -  [Screening](../ObjectsExampleCode/src/main/java/com/wooklab/example/chapter04/Screening.java)


---

### 02 설계 트레이드오프

#### 캡슐화

- 객체지향에서 가장 중요한 원리 `캡슐화`
    - 상태와 행동을 하나의 객체 안에 모으는 이유는
        - 객체의 내부 구현을 외부로부터 감추기 위해
        - 구현이란 나중에 변경될 가능서잉 높은 어떤 것을 가리킴
    - 변경 가능성이 높은 부분은 내부에 숨기고 외부에는 상대적으로 안정적인 부분만 공개함으로써 `변경의 여파를 통제`
    - 변경될 가능성이 높은 부분을 구현이라 부름
    - 상대적으로 안정적인 부분을 인터페이스라 부름
    - 객체 설계의 기본
        - 변경의 정도에 따라 구현과 인터페이스를 분리
        - 외부에서는 인터페이스에만 의존하도록 관계를 조절
    - 캡슐화는 외부에서 알 필요가 없는 부분을 감춤으로써 대상을 단순화하는 추상화의 한 종류
    - 가장 중요한 원리
        - 불안정한 구현 세부사항을 안정적인 인터페이스 뒤로 캡슐화

<br/>

#### 응집도와 결합도
- 응집도
    - 응집도는 모듈에 포함된 내부 요소들이 연관돼 있는 정도를 나타냄
    - 하나의 목적을 위해 긴밀하게 협력한다면 그 모듈은 `높은 응집도`
    - 서로 다른 목적을 추구한다면 `낮은 응집도`
- 결합도
    - 의존성의 정도를 나타내며 다른 모듈에 대해 얼마나 많은 지식을 갖고 있는지를 나타내는 척도
    - 어떤 모듈이 다른 모듈에 대해 너무 자세한 부분까지 알고 있다면 두 모듈은 `높은 결합도`
    - 꼭 필요한 지식만 알고 있다면 두 모듈은 `낮은 결합도`
- 높은 응집도와 낮은 결합도를 가진 설계를 추구해야 하는 이유
    - 설계를 변경하기 쉽게 만든다
    - 변경이 발생할 때 모듈 내부에서 발생하는 변경의 정도 측정 가능
    - 높은 응집도
        - 하나의 변경을 수용하기 위해 모듈 전체가 함께 변경된다면 응집도가 높은 것 <br/>
        -> 일부만 변경된다면 응집도가 낮은 것
        - 하나의 변경에 대해 하나의 모듈만 변경된다면 응집도가 높지만<br/>
        -> 다수의 모듈이 함꼐 변경돼야 한다면 응집도가 낮은 것
        - 응집도가 높을수록 변경의 대상과 범위가 명확해지기 때문에 코드의 변경하기 쉬워짐
    - 낮은 결합도
        - 한 모듈이 변경되기 위해서 다른 모듈의 변경을 요구하는 정도
        - 하나의 모듈을 수정할 때 얼마나 많은 모듈을 함께 수정해야 하는지를 나타냄
        - 결합도가 높을수록 함께 변경해야하는 모듈의 수가 높아지고 변경이 어려워짐
        - 퍼블릭 인터페이스를 수정했을 때만 다른 모듈에 영향을 미치는 경우  결합도가 낮다고 표현
- 응집도와 결합도를 변경의 관점에서 바라보는 것은 설계에 대한 시각을 변화시킬 것이다
- 캡슐화의 정도가 응집도와 결합도에 영향을 미친다
        

---

### 03 데이터 중심의 영화 예메 시스템의 문제점
- 데이터 중심의 설계는 캡슐화를 위반하고 **객체의 내부 구현을 인터페이스의 일부로 만들고**
- 반면 책임 중심의 설계는 **객체의 내부 구현을 안정적인 인터페이스 뒤로 캡슐화**
- 데이터 중심의 설계가 가진 대표적인 문제점
    - 캡슐화 위반
    - 높은 결합도
    - 낮은 응집도

<br/>

#### 캡슐화 위반

데이터 중심으로 설계한 Movie 클래스
```java
public class Movie {
    private Monecy fee;

    public Money getFee() {
        return fee;
    }

    public void setFee(Money fee) {
        this.fee = fee;
    }
}
```
> 오직 메서드를 통해서만 객체의 내부 상태에 접근 <br/>
> 그러나 객체 내부의 변수가 존재한다는 사실이 퍼블릭 인터페이스에 나타나기 때문에 캡슐화된 것은 아니다

- 추측에 의한 설계 전략(design-by-guessing strategy)<br/>
    - 접근자와 수정자에 과도하게 존하는 설계 방식<br/>
    - 막연한 추측을 기반으로 설계<br/>
    - 캡슐화의 원칙을 위반하는 변경에 취약한 설계

<br/>

#### 높은 결합도

> 객체 내부의 구현이 객체의 인터페이스에 드러난다는 것은 **클라이언트가 구현에 강하게 결합**된다는 것을 의미<br/>
> (객체 내부 구현을 변경 -> 인터페이스에 의존하는 모든 클라이언트들도 변경)

- 데이터 중심 설계는 
    - 객체의 캡슐화를 약화시키기 때문에 클라이언트가 객체의 구현에 강하게 결합
    - 여러 데이터 객체들을 사용하는 제어 로직이 특정 객체 안에 집중되기 때문에 **하나의 제어 객체가 다수의 객체에 강하게 결합**되는 단점
    - 이 결합도로 인해 어떤 데이터 객체를 변경하더라도 **제어 객체를 함께 변경**
    - 전체 시스템을 하나의 거대한 의존성 덩어리로 만들어 버림
        - 때문에 어떤 변경이라도 일단 발생하고나면 시스템 전체 영향

<br/>

#### 낮은 응집도

> 서로 다른 이유로 변경되는 코드가 하나의 모듈 안에 공존할 때 모듈의 응집도가 낮다고 말한다.

- 낮은 응집도는 두 가지 측면에서 설계에 문제
    - 변경과 아무 상관이 없는 코드들이 영향
    - 하나의 요구사항 변경을 반영하기 위해 동시에 여러 모듈을 수정
- 설계의 응집도가 낮다는 증거
    - 어떤 요구사항 변경을 수용하기 위해 하나 이상의 클래스를 수정해야 할 경우

#### 단일 책임 원칙(Single Responsibility Principle, SRP)
- 클래스는 단 한 가지의 변경 이유만 가져야 한다
- 클래스의 응집도를 높일 수 있는 설계의 원칙
- 단일 책임 원칙에서의 `책임`은 역할, 책임, 협력에서의 책임이 아닌 `변경(변경의 이유)`과 관련된 더 큰 개념을 가리킴


---

### 04 자율적인 객체를 통해

#### 캡슐화를 지켜라

- 객체는 자신이 어떤 데이터를 가지고 있는지를 내부에 캡슐화하고 외부에 공개해서는 안됨
- 객체는 스스로의 상태를 책임져야 하며 외부에서는 인터페이스에 정의된 메서드를 통해서만 상태에 접근
- 객체에게 의미 있는 메서드는 객체가 책임져야 하는 무언가를 수행하는 메서드
- 속성이 가시성을 private으로 설정했다고 해도
    - 접근자와 수정자를 통해 속성을 외부로 제공하고 있다면 `캡슐화 위반`

> 사각형을 표현하는 Rectangle 클래스
```java
class Rectangle {
    private int left;
    private int top;
    private int right;
    private int bottom;

    public Rectangle(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    // 이하 getter, setter 생략...
}
```
- 이 코드의 문제점
    - '코드 중복'이 발생할 확률이 높다
    - '변경에 취약'하다
- Rectangle 내부에 너비와 높이를 조절하는 로직을 캡슐화하면 문제 해결
```java
class Rectangle {
    public void enlarge(int multiple) {
        right *= multiple;
        bottom *= multiple;
    }
}
```
- 변경하는 주체를 외부 객체에서 Rectangle 내부로 이동
- Rectangle 스스로 증가시키도록 `'책임을 이동'` 시킴

<br/>

#### 스스로 자신의 데이터를 책임지는 객체
- 객체 내부에 저장되는 데이터보다 객체가 협력에 참여하면서 수행할 책임을 정의하는 오퍼레이션이 더 중요
- 객체를 설계할 때 다음과 같은 두 질문을 하자
    1. 이 객체가 어떤 데이터를 포함해야 하는가? `(질문1)`
    2. 이 객체가 데이터에 대해 수행해야 하는 오퍼레이션은 무엇인가? `(질문2)`
- 위 질문을 조합하면 객체의 내부 상태를 저장하는 방식과 저장된 상태에 대해 호출할 수 있는 오퍼레이션의 집합을 얻을 수 있음

> DiscountCondition 클래스
```java
public class DiscountCondition {
    private DiscountConditionType type;
    private int sequence;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
}
```
- `질문1.` 할인 조건 유형과 유형에 따라 필요한 데이터<br/>
- `질문2.` 할인 조건에는 순서 조건과 기간 조건의 두 가지 종류가 존재<br/>
    - 즉, 두 가지 할인 조건을 판단할 수 있는 두 개의 isDiscountable 메서드 필요

> Movie 클래스
```java
public class Movie {
    private String title;
    private String Duration runningTime;
    private Money fee;
    private List<DiscountCondition> discountConditions;

    private MovieType movieType;
    private Money discountAmount;
    private double discountPercent;
}
```
- `질문1.` 영화 제목, 시간, 요금, 할인조건 등
- `질문2.` 영화 요금을 계산하는 오퍼레이션과 할인여부를 판단하는 오퍼레이션 
필요
    - 요금을 계산하기 위해서는 할인 정책을 염두
    - 할인 정책에는 금액 할인, 비율 할인, 할인 미적용 세가지 타입 존재
    - 할인 정책의 타입을 반환하는 getMovieType 메서드와 정책별로 요금을 계산하는 세 가지 메서드 구현해야함
    - DiscountCondition의 목록을 포함하기 떄문에, 할인 여부를 판단하는 오퍼레이션 isDiscountable 메서드 필요

=> 이러한 과정을 통해 결합도 측면에서 ReservationAgency에 몰려있던 의존성이 개선<br/>
=> 기존 설계보다 내부 구현을 더 면밀하게 캡슐화하고 있기 때문


---

### 05 하지만 여전히 부족하다

#### 캡슐화 위반

#### 높은 결합도

#### 낮은 응집도


---

### 06 데이터 중심 설계의 문제점

#### 데이터 중심 설계는 객체의 행동보다는 상태에 초점을 맞춘다

#### 데이터 중심의 설계는 객체를 고립시킨 채 오퍼레이션을 정의하도록 만든다

