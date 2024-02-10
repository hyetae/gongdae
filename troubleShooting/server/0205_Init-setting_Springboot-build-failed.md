```
A problem occurred configuring root project

> Could not resolve all files for configuration

  ㅤㅤ> Could not resolve org.springframework.boot:spring-boot-gradle-plugin:3.2.2.
```

1. 상황 파악
    
    Springboot 빌드가 되지 않음
    
2. 문제 발생 출처 확인
    
    Springboot와 JAVA의 버전이 맞지 않아 오류 발생
    
3. 문제 발생 조건 파악
    
    Springboot 3.2.2. | JAVA 11
    
    위와 같은 환경으로 빌드하려 했으나 Springboot 3.x부터는 JAVA 17만을 지원함
    
4. 원인 상정 및 검증
    
    OS 내 모든 자바를 지우고 JAVA 17 설치 후 빌드 성공
