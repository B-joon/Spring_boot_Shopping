# Junit5 테스트 시 사용하는 어노테이션 및 로직 설명
### @SpringBootTest
통합 테스트를 위해 스프링 부트에서 제공하는 어노테이션<br>
신제 애플리케이션을 구동할 때처럼 모든 Bean을 IoC 컨테이너에 등록한다.
애플리케이션의 규모가 크면 느려질 수 있다.

### @TestPropertySource(locations = "classpath:프로퍼티명")
테스트 코드 실행 시 application.properties에 설정해둔 값보다 application-test.properties 에 같은 설정이 있다면
더 높은 우선순위를 부여한다. 기존에는 MySql을 사용했지만 테스트 코드 실행 시에는 H2DB를 사용한다.

### @Test
테스트할 메소드 위에 선언하여 해당 메소드를 테스트 대상으로 지정한다.

### @DisplayName("테스트 이름")
Junit5에 추가된 어노테이션으로 테스트 코드 실행 시 지정한 테스트명이 노출된다.

