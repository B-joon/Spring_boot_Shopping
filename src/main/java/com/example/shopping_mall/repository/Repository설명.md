# JpaRepository
Spring Data JPA에서는 엔티티 매니저를 직접 이용해 코드를 작성하지 않아도 된다
Repository 인터페이스에 간단한 네이밍 룰을 이용하여 메소드를 적성하면 원하는 쿼리를 실행할 수 있다.

#### JpaRepository에서 지원하는 메소드 예시
| 메소드                          | 기능          |
|:-----------------------------|:------------|
| <S extends T> save(S entity) | 엔티티 저장 및 수정 |
| void delete(T entity)        | 엔티티 삭제      |
| count()                      | 엔티티 총 개수 반환 |
| Iterable<T> findAll()        | 모든 엔티티 조회   |

#### 쿼리 메소드 Sample 및 JPQL snippet
![쿼리 메소드 Sample 및 JPQL snippet](https://user-images.githubusercontent.com/75296934/218892548-da732d0d-fb07-4bea-a853-58acb7886d2e.PNG)

#### 메소드 네이밍
itemNm(상품명)으로 데이터를 조회하기 위해서 By 뒤에 필드명인 itemNm을 메소드의 이름을 붙인다.
엔티티명은 생량이 가능하므로 findByItemNm으로 메소드 명을 만들어주고 매개 변수로는 검색할 때 사용할 상품명
변수를 넘겨준다.

출력 결과를 OrderBy 키워드를 이용한다면 오름차순 또는 내림차순으로 조회할 수 있다.
오름차순인 경우 'OrderBy + 속성명 + Asc 키워드'를 이용하고,
내림차순에서는 OrderBy + 속성명 + Desc 키워드'를 이용해 데이터 순서를 처리할 수 있다.

## @Query
@Query 어노테이션을 이용하면 SQL과 유사한 JPQL(java Persistence Query Language)이라는
객체지향 쿼리 언어를 통해 복잡한 쿼리도 처리 가능하다. SQL과 문법 자체가 유사하기 때문에
기존 SQL 사용자는 접근이 쉽다. 

### JPQL
JPQL은 엔티티 객체를 대상으로 쿼리를 수행한다.
테이블이 아닌 객체를 대상으로 검색하는 객체지향 쿼리이다.

JPQL은 SQL을 추상화해서 사용하기 때문에 특정 DB 문법에 의존하지 않고 데이터베이스가
변경되어도 애플리케이션이 영향을 받지 않는다.

### @Param
@Param 어노테이션을 이용하여 변수를 JPQL 에 전달하는 대신 파라미터의 순서를 이용해 전달해줄 수 있다.
":파라미터명"대신 첫 번째 파라미터를 전달하겠다는 "?1"이라는 표현을 사용할 수 있지만
파라미터 순서가 변하면 동작하지 않을 수 있으므로 명시적은 방법을 사용하는게 좋다.

#### nativeQuery 속성
nativeQuery 속성을 사용하면 연결된 DB의 쿼리를 그대로 사용이 가능하다.
옵션 : true

## Querydsl
@Query 어노테이션 안에 JPQL문법으로 문자열을 입력하기 때문에 컴파일 시점에서 에러를 발견할 수 없다.
이를 보완할 수 있는 방법이 Querydsl이다.

Querydsl 클래스 파일을 생성하기 위해서 maven or gradle의 컴파일 명령을 실행한다.

Querydsl을 Spring Data JPA와 함께 사용하기 위해서는 사용자 정의 리포지토리를 정의해야 한다.
 1. 사용자 정의 인터페이스 작성
 2. 사용자 정의 인터페이스 구현
 3. Spring Data JPA 리포지토리에서 사용자 정의 인터페이스 상속

### Querydsl 장점
JPQL을 코드로 작성할 수 있도록 도와주는 빌더 API 이다.
소스코드로 SQL문을 문자열이 아닌 코드로 작성하기 때문에 컴파일러의 도움을 받을 수 있고
오타가 있음을 바로 알려준다. 또한 동적으로 쿼리를 생성해주는게 가장 큰 장점이다.

 * 고정된 SQL문이 아닌 조건에 맞게 동적으로 쿼리를 생성할 수 있다.
 * 비슷한 쿼리를 재사용할 수 있으며 제약 조건 조립 및 가독성을 향상시킬 수 있다.
 * 문자열이 아닌 자바 소스코드로 작성하기 때문에 컴파일 시점에 오류를 발견할 수 있다.
 * IDE의 도움을 받아서 자동 완성 기능을 이용할 수 있기 때문에 생산성을 향상시킬 수 있다.

#### JPAQuery 데이터 변환 메소드
| 메소드                           | 기능                                                    |
|:------------------------------|:------------------------------------------------------|
| List<T> fetch()               | 조회 결과 리스트 반환                                          |
| T fetchOne()                  | 조회 대상이 1건인 경우 제네릭으로 지정한 타입 반환<br>조회 대상이 1건 이상이면 에러 발생 |
| T fetchFirst()                | 조회 대상이 1건 또는 1건 이상이면 1건만 반환                           |
| Long fetchCount()             | 해당 조회 데이터 전체 개수 반환, count 쿼리 실행                       |
| QueryResult<T> fetchResults() | 조회한 리스트와 전체 개수를 포함한 QueryResults 반환                   |

#### Repository에 QueryDslPredicateExecutor 인터페이스 상속

##### QueryDslPredicateExecutor 인터페이스 정의 메소드
| 메소드                                  | 기능                  |
|:-------------------------------------|:--------------------|
| long count(Predicate)                | 조건에 맞는 데이터의 종 개수 반환 |
| boolean exists(Predicate)            | 조건에 맞는 데이터 존재 여부 반환 |
| Iterable findAll(Predicate)          | 조건에 맞는 모든 데이터 반환    |
| Page<T> findAll(Predicate, Pageable) | 조건에 맞는 페이지 데이터 반환   |
| Iterable findAll(Predicate, Sort)    | 조건에 맞는 정렬된 데이터 반환   |
| T findOne(Predicate)                 | 조건에 맞는 데이터 1개 반환    |

### 기능 + 'SearchVO' 변수 별 데이터 조회 방법
##### private String searchDateType
현재 시간과 등록일을 비교해서 데이터를 조회한다.
 * 조회 시간 기준
   * all : 상품 등록일 전체
   * 1d : 최근 하루 동안 등록된 상품
   * 1W : 최근 일주일 동안 등록된 상품
   * 1m : 최근 한달 동안 등록된 상품
   * 6m : 최근 6개월 동안 등록된 상품
##### private String searchBy
데이터를 조회할 때 어떤 유형으로 조회할지 선택한다.
 * 기능 + "Nm" : 기능명
 * createdBy : 상품 등록자 아이디
##### private String searchQuery = ""
조회할 검색어 저장할 변수<br>
searchBy가 기능명일 경우 기능명을 기준으로 검색하고, createdBy일 경우 상품 등록자 아이디 기준으로 검색