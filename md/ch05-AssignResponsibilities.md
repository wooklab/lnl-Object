# 05 책임 할당하기
- 책임에 초점을 맞춰서 설계할 때 직면하는 가장 큰 어려움은 어떤 객체에게 어떤 책임을 할당할지를 결정하기가 쉽지 않다는 것
- 이번 장에서 살펴볼 GRASP 패턴은 책임 할당의 어려움을 해결하기 위한 답을 제시

<br/>

### 01 책임 주도 설계를 향해

- `데이터 중심 설계 -> 책임 중심의 설계` 로 전환하기 위한 원칙
    1. 데이터보다 행동을 먼저 결정하라
    2. 협력이라는 문맥 안에서 책임을 결정하라

#### 데이터보다 행동을 먼저 결정하라

- 클라이언트의 관점에서 객체가 수행하는 행동이란 곧 객체의 책임을 의미
- 책임 중심의 설계에서는 "이 객체가 수행해야 하는 책임은 무엇인가"를 결정한 후에
- "이 책임을 수행하는 데 필요한 데이터는 무엇인가"를 결정
- 책임 중심의 설계에서는 객체의 행동, 즉 책임을 먼저 결정한 후에 객체의 상태를 결정

<br/>

#### 협력이라는 문맥 안에서 책임을 결정하라

- 책임과 협력
    - 책임은 객체의 입장이 아니라 객체가 참여하는 협력에 적합해야 함
    - 메시지를 전송하는 클라이언트의 의도에 적합한 책임을 할당해야 함<br/>
      (메시지 수신자가 아니라 메시지 전송자에게 적합한 책임)
    - 객체가 메시지를 선택하는 것이 아니라 메시지가 객체를 선택
- 메시지가 클라이언트의 의도를 표현한다
    - 객체를 결정하기 전에 객체가 수신할 메시지를 먼저 결정
    - 메시지를 수신하기로 결정된 객체는 메시지를 처리할 `'책임'`을 할당받음
- 메시지를 먼저 결정
    - 메시지 송신자는 메시지 수신자에 대한 어떠한 가정도 할 수 없음
    - 메시지 전송자의 관점에서 메시지 수신자가 깔끔하게 캡슐화 됨
- 책임 중심의 설계에서는 협력이라는 문맥 안에서 객체가 수행할 책임에 초점

<br/>

#### 책임 주도 설계

> 책임 주도 설계의 흐름
> - 시스템이 사용자에게 제공해야 하는 기능인 시스템 책임 파악
> - 시스템 책임을 더 작은 책임으로 분할
> - 분할된 책임을 수행할 수 있는 적절한 객체 또는 역할을 찾아 책임 할당
> - 객체가 책임을 수행하는 도중 다른 객체의 도움이 필요한 경우 이를 책임질 적절한 객체 또는 역할을 찾음
> - 해당 객체 또는 역할에게 책임을 할당함으로써 두 객체가 협력

- 책임 주도 설계의 핵심은 책임을 결정한 후에 책임을 수행할 객체를 결정하는 것
- 협력에 참여하는 객체들의 책임이 어느 정도 정리될 때까지는 객체의 내부 상태에 대해 관심을 가지지 않는 것


---

### 02 책임 할당을 위한 GRASP 패턴

> GRASP 패턴 (General Responsibility Assignment Software Pattern, GRASP Pattern)<br/>
> -크레이그 라만(Craig Larman)

#### 도메인 개념에서 출발하기
어떤 책임을 할당해야 할 때 가장 먼저 고민해야 하는 유력한 후보는 바로 도메인 개념

![chpter05_pic 5.1](../img/pic_5.1.jpeg)
- 영화 예매 시스템을 구성하는 도메인 개념과 개념 사이의 관계를 대략적으로 표현
- 설게를 시작하기 위해 참고할 수 있는 개념들의 모음 정도로 간주
- 도메인 개념을 정리하는 데 너무 많은 시간을 들이지 말고 빠르게 설계와 구현을 진행하라
    - 도메인 모델이 `구현을 염두해 두고 구조화`되는 것이 바람직
    - 실제 코드를 구현하면서 얻게 되는 통찰이 역으로 도메인에 대한 개념을 바꾸기 때문

<br/>

#### 정보 전문가에게 책임을 할당하라

#### 높은 응집도와 낮은 결합도

#### 창조자에게 객체 생성 책임을 할당하라

---

### 03 구현을 통한 검증

#### DiscountCondition 개선하기

#### 타입 분리하기

#### 다형성을 통해 분리하기

#### 변겨으로부터 보호하기

#### Movie 클래스 개선하기

#### 변경과 유연성

---

### 04 책임 주도 설계의 대안

#### 메서드 응집도

#### 객체를 자율적으로 만들자