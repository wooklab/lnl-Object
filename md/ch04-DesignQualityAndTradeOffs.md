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
        - 결합도가 높을수록 함께 변경해야하는 모듈의 수가 높아짐
        - 변경이 어려워짐
        - 퍼블릭 인터페이스를 수정했을 때만 다른 모듈에 영향을 미치는 경우 겨합도가 낮다고 표현
- 응집도와 결합도를 변경의 관점에서 바라보는 것은 설계에 대한 시각을 변화시킬 것이다
- 캡슐화의 정도가 응집도와 결합도에 영향을 미친다
        

---

### 03 데이터 중심의 영화 예메 시스템의 문제점
