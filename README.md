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