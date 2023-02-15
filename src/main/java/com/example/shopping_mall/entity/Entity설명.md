# Entity

#### 엔티티 매핑 관련 어노테이션
| 어노테이션              | 설명                         |
|:-------------------|:---------------------------|
| @Entity            | 클래스를 엔티티로 선언               
| @Table             | 엔티티와 매핑할 테이블 지정            |
| @Id                | 테이블의 기본키에 사용할 속성을 지정       |
| @GeneratedValue    | 키 값을 생성하는 전략 명시            |
| @Column            | 필드와 컬럼 매핑                  |
| @Lob               | BLOB, CLOB 타입 매핑           |
| @CreationTimestamp | inset시 시간 자동 저장            |
| @UpdateTimestamp   | update시 시간 자동 저장           |
| @Enumerated        | enum 타입 매핑                 |
| @transient         | 해당 필드 데이터베이스 매핑 무시         |
| @Temporal          | 날짜 타입 매핑                   |
| @CreateDate        | 엔티티가 생성되어 저장될 때 시간 자동 저장   |
| @LastModifiedDate  | 조회한 엔티티의 값을 변경할 때 시간 자동 저장 |

#### CLOB와 BLOB의 의미
```
CLOB란 사이즈가 큰 데이터를 외부 파일로 저장하기 위한 데이터이다.
문자형 대용향 파일을 저장하는데 사용하는 데이터 타입이라고 생각하면 된다.
BLOB란 바이너리 데이터를 DB 외부에 저장하기 위한 타입이다. 이미지, 사운드, 비디오
같은 멀티 미디어 데이터를 다룰때 사용할 수 있다.
```

## @Column 속성
| 속성               | 설명                                                                                                               | 기본값       |
|:-----------------|:-----------------------------------------------------------------------------------------------------------------|:----------|
| name             | 필드와 매핑할 컬럼의 이름 설정                                                                                                | 객체의 필드 이름 |
| unique(DDL)      | 유니크 제약 조건 설정                                                                                                     |           |
| insertable       | insert 기능 여부                                                                                                     | true      |
| updatable        | update 기능 여부                                                                                                     | true      |
| length           | String 타입의 문자 길이 제약조건 설정                                                                                         | 255       |
| nullable         | null 값의 허용 여부 설정. false 설정 시 DDL 생성 시에 not null 제약조건 추가                                                          ||
| columnDefinition | 데이터베이스 컬럼 정보 직접 기술<br/>@Column(columnDefinition = "valchar(s)<br/>default'10' not null"                          ||
| precision        | BigDecimal 타입에서 사용(BingInteger 가능) precision은 소수점을 포함한 전체 자리수이고, scale은 소수점 자리수.<br/>Double과 float 타입에는 적용되지 않음. ||

#### DDL의 의미
```
DDL(Data Definition Language)이란 테이블, 스키마, 인덱스, 뷰, 도메인을 정의, 변경, 제거할 때 사용하는 언어이다.
가령, 테이블을 생성하거나 삭제하는 CREATE, DROP 등이 이에 해당한다.
```
@Entity 어노테이션은 클래스의 상단에 JPA에 언티티 클래스라는 것을 알려준다.
Entity 클래스는 반드시 기본키를 가져야해서 @Id 어노테이션을 이용해 id 맴버 변수를 상품 테이블의 기본키로 설정한다.

#### @GeneratedValue 기본키 생성하는 전략
| 생성 전략                         | 설명                                                                  |
|:------------------------------|:--------------------------------------------------------------------|
| GenerationType.AUTO (default) | JPA 구현체가 자동으로 생성 전략 결정                                              |
| GenerationType.IDENTITY       | 기본키 생성을 데이터베이스에 위임<br/>MySql 데이터베이스의 경우 AUTO_INCREMENT를 사용하여 기본키 생성 |
| GenerationType.SEQUENCE       | 데이터베이스 시퀀스 오브젝트를 이용한 기본키 생성<br/>@SequenceGenerator를 사용하여 시퀀스 등록 필요  |
| GenerationType.TABLE          | 키 생성용 테이블 사용. @TableGenerator 필요                                    |
전략은 기본키를 생성하는 방법이라고 이해하면 된다.

#### 기본키와 데이터베이스 시퀀스 오브젝트의 의미
````
기본키(primary key)는 데이터베이스에서 조건을 만족하는 튜플을 찾을 때 다른 튜플들과 유일하게 구별할 수 있도록
기준을 세워주는 속성입니다. 예를 들어 상품 데이터를 찾을 때 상품의 ID를 통해서 다른 상품들과
구별을 할 수 있다.
데이터베이스 시퀀스 오브젝트에서 시퀀스란 순차적으로 증가하는 값을 반환해주는 데이터베이스
객체입니다. 기본키 중복값을 방지하기 위해 사용한다.
````