# 엔티티 매핑 관련 어노테이션

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