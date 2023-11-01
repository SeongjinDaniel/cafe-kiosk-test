코드 : https://github.com/wbluke/practical-testing/tree/lesson2-4
JUnit5 : https://junit.org/junit5
AssertJ : https://joel-costigliola.github.io/assertj/index.html

### 단위 테스트
- 작은 코드 단위(클래스, 메서드)를 독립적으로 검증하는 테스트
- 검증 속도가 빠르고, 안정적

### JUNIT5
- 단위 테스트를 위한 테스트 프레임워크
- XUnit - kent Beck
  - SUnit(Samlltalk), JUnit(Java), NUnit(.NET), ...
### AssertJ
- 테스트 코드 작성을 원활하게 돕는 테스트 라이브러리
- 풍부한 API, 메서드 체이닝 지원
### Test Driven Development(TDD)
- RED: 실패하는 테스트작성
- GREEN: 테스트 통과 최소한의 코딩
- REFACTOR: 구현 코드 개선, 테스트 통과 유지
위 3개의 과정을 순서대로 구현하며 코딩 하는 방법을 말함

#### 선 기능 구현, 후 테스트 작성
- 테스트 자체의 누락 가능성
- 특정 테스트 케이스(해피 케이스)만 검증할 가능성
- 잘못된 구현을 다소 늦게 발견할 가능성
#### 선 테스트 작성, 후 기능 구현 
- 복잡도가 낮은(유연하며 유지보수가 쉬운), 테스트 가능한 코드로 구현할 수 있게 한다.
- 쉽게 발견하기 어려운 엣지(Edge) 케이스를 놓치지 않게 해준다.
- 구현에 대한 빠른 피드백을 받을 수 있다.
- 과감한 리팩토링이 가능해진다.

### 테스트는 문서다
- 프로덕션 기능을 설명하는 테스트 코드 문서
- 다양한 테스트 케이스를 통해 프로덕션 코드를 이해하는 시각과 관점을 보완
- 어느 한사람이 과거에 경험했던 고민의 결과물을 팀차원으로 승격시켜서, 모두의 자산으로 공유할 수 있다.

### DisplayName을 섬세하게
- "음료 1개 추가 테스트" 보다 "음료를 1개 추가할 수 있다"가 더 좋다.
- ~테스트라고 짓는것은 지양
- "음료를 1개 추가할 수 있다"보다 "음료를 1개 추가하면 주문 목록에 담긴다"가 더 좋다.(테스트 행위에 대한 결과까지 서술)
- "특정시간 이전에 주문을 생성하면 **실패한다**(X)" 보다 "**영업 시작 시간** 이전에는 주문을 생성할 수 없다"가 더 좋다.(도메인 용어를 사용하여 한층 추상화된 내용을 담기)
  - 메서드 자체의 관점보다 도메인 정책 관점으로
  - 테스트의 현상을 중점으로 기술하지 말 것 

### BDD(Behavior Driven Development)
- TDD에서 파생된 개발 방법
- 함수 단위의 테스트에 집중하기보다, 시나리오에 기반한 **테스트케이스(TC)** 자체에 집중하여 테스트 한다.
- 개발자가 아닌 사람이 봐도 이해할 수 있을 정도의 추상화 수준(레벨)을 권장

### Given / When / Then
- Given: 시나리오 진행에 필요한 모든 준비 과정 (객체, 값, 상태 등)
  - 어떤환경에서
- When: 시나리오 행동 진행
  - 어떤 행동을 진행했을 때
- Then: 시나리오 진행에 대한 결과 명시, 검증
  - 어떤 상태 변화가 일어난다
**DisplayName에 명확하게 작성할 수 있다**

### 키워드 정리
- @DisplayName - 도메인 정책, 용어를 사용한 명확한 문장
- Given / When / Then - 주어진 환경, 행동, 상태 변화
- TDD vs. BDD
- JUnit vs. Spock(Groovy 언어 테스트)
**언어가 사고를 제한한다.** **문서로서의 테스트를 작성하는 것을 추천한다.**

### Layered Architechture
user -> Presentation Layer Business Layer -> Persistence Layer -> DB
     <-                                   <-                   <-
- **관심사 분리** 및 책임을 나누기 위해서 레이어드 아키텍처를 구현한다
- 테스트 하기 복잡해 보인다????

### 통합 테스트
- 여러 모듈이 협력하는 기능을 통합적으로 검증하는 테스트
- 일반적으로 작은 범위의 단위 테스트만으로는 기능 전체의 신뢰성을 보장할 수 없다.
- 풍부한 단위 테스트 & 큰 기능 단위를 검증하는 통합 테스트

### 요구사항
- 키오스크 주문을 위한 상품 후보 리스트 조회하기
- 상품의 판매 상태: 판매중, 판매보류, 판매중지
  - 판매중, 판매보류인 상태의 상품을 화면에 보여준다
- id, 상품 번호, 상품 타입, 판매 상태, 상품 이름, 가격

### h2 db 연결 참고
- 첫 연결시에 사용
  - jdbc:h2:mem:~/cafeKioskApplication
- 아래 와같이 사용해야함
  - jdbc:h2:tcp://localhost/~/cafeKioskApplication  <-- network 모드로 연결

### Persistence Layer
- Data Access의 역할
- 비즈니스 가공 로직이 포함되어서는 안 된다.
- Data에 대한 CRUD에만 집중한 레이어
- **트랜잭션**을 보장해야 한다.

### 요구사항
- 상품 번호 리스트를 받아 주문 생성하기
- 주문은 주문 상태, 주문 등록 시간을 가진다.
- 주문의 총 금액을 계산할 수 있어야 한다.

- 주문 생성 시 재고 확인 및 개수 차감 후 생성하기
- 재고는 상품번호를 가진다.
- 재고와 관련 있는 상품 타입은 병 음료, 베이커리이다.

###  Presentation Layer
- 외부 세계의 요청을 가장 먼저 받는 계층
- 파라미터에 대한 최소한의 검증을 수행한다.

### MockMvc
- Mock(가짜) 객체를 사용해 스프링 MVC 동작을 재현할 수 있는 테스트 프레임워크

### 요구사항
- 관리자 페이지에서 신규 상품을 등록할 수 있다.
- 상품명, 상품 타입, 판매 상태, 가격 등을 입력받는다.


### 키워드 정리

- Layered Architechture
- **Hexagonal Architecture**
- 단위 테스트 vs. 통합 테스트
- IoC, DI, AOP
- ORM, 패러다임의 불일치, Hibernate
- Spring Data JPA

<img width="723" alt="image" src="https://github.com/SeongjinOliver/mytube/assets/55625864/66fb3155-f3f8-457e-b541-9b0107dbbb62">

- @SpringBootTest vs. @DataJpaTest
- @SpringBootTest vs. @WebMvcTest
- @Transactional (readOnly = true)
- Optimistic Lock, Pessimistic Lock
- CQRS
- RestControllerAdvice, @ExceptionHandler
- Spring bean validation
  - @NotNull, @NotEmpty, @NotBlank, ...
  - @WebMvcTest
  - @ObjectMapper
  - Mock, Mockito, @MockBean

# Test Double
- Stunt Double : 영화에서 대역
- 참고
  - https://martinfowler.com/articles/mocksArentStubs.html
    - Test Double의 5가지 종류
      - Dummy
        - 아무것도 하지 않는 깡통 객체
      - Fake
        - 단순한 형태로 동일한 기능은 수행하나, 프로덕션에서 쓰기에는 부족한 객체 (ex. FakeRepository)
      - Stub -> 상태 검증 (State Verification)
        - 테스트에서 요청한 것에 대해 미리 준비한 결과를 제공하는 객체 그외에는 응답하지 않는다.
      - Spy 
        - Stub이면서 호출된 내용을 기록하여 보여줄 수 있는 객체, 일부는 실제 객체처럼 동작히키고 일부만 Stubbing할 수 있다.
      - Mock -> 행위 검증 (Behavior Verification)
        - 행위에 대한 기대를 명세하고, 그에 따라 동작하도록 만들어진 객체

- 키워드 정리
  - Test Double, Stubbing
    - dummy, fake, stub, spy, mock
  - @Mock, @MockBean, @Spy, @SpyBean, @InjectMocks
  - BDDMockito
  - Classicist VS. Mockist

- Test Fixture
  - Fixture: 고정물, 고정되어 있는 물체 
  - 테스트를 위해 원하는 상태로 고정시킨 일련의 객체

- 테스트 수행도 비용이다. 환경 통합하기

- 테스트에서만 필요한 메서드가 생겼는데 프로덕션 코드에서는 필요 없다면?
    - 만들어도 된다. 하지만 **보수적**으로 접근하기!

- 키워드 정리
    - 테스트 하나 당 목적은 하나!
    - 완벽한 제어
    - 테스트 환경의 독립성, 테스트 간 독립성
    - Test Fixture
    - deleteAll(), deleteAllInBatch()
    - @ParameterizedTest, @DynamicTest
    - 수행 환경 통합하기(테스트 수행하는 것 자체가 비용이니까 서버가 적게 뜨는 횟수를 줄여라)
    - private method test
    - 테스트에서만 필요한 코드

- 학습 테스트
    - 잘 모르는 기능, 라이브러리, 프레임워크를 학습하기 위해 작성하는 테스트
    - 여러 테스트 케이스를 스스로 정의하고 검증하는 과정을 통해 보다 구체적인 동작과 기능을 학습할 수 있다.
    - 관련 문서만 읽는 것보다 훨씬 재미있게 학습할 수 있다.