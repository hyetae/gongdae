```
org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name
'org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaConfiguration': Unsatisfied dependency
expressed through constructor parameter 0: Error creating bean with name 'dataSource': Failed to
replace DataSource with an embedded database for tests. If you want an embedded database please put
a supported one on the classpath or tune the replace attribute of @AutoConfigureTestDatabase.
```

1. 상황 파악
    @DataJpaTest를 이용해 JPA 테스트를 하려고 했음
    
2. 문제 발생 출처 확인
    Datasource를 내장 데이터베이스로 교체하는데 실패함
    
3. 문제 발생 조건 파악
    @DataJpaTest 는 기본적으로 **내장된 메모리 데이터베이스**를 이용해 테스트를 실행함
    그러나 **물리적인 MySQL 데이터베이스**를 연결해서 테스트 하려다 문제 발생
    
4. 원인 상정 및 검증
    `@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)`를
   추가하여 EmbeddedDatabase를 찾아 설정하지 않고 기존 애플리케이션에서 사용한 DataSource가 등록되게 함
