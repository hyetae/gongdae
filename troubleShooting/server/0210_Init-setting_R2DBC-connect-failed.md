```
---

APPLICATION FAILED TO START

---

Description:

Failed to configure a ConnectionFactory: 'url' attribute is not specified and no embedded database could be configured.

Reason: Failed to determine a suitable R2DBC Connection URL

Action:

Consider the following:
If you want an embedded database (H2), please put it on the classpath.
If you have database settings to be loaded from a particular profile you may need to activate it (no profiles are currently active).

Process finished with exit code 1
```
1. 상황 파악
    
    Springboot 빌드가 되지 않음
    
2. 문제 발생 출처 확인
    
    Springboot가 R2DBC 연결 URL을 확인하지 못함
    
3. 문제 발생 조건 파악
    
    @SpringBootApplication에서 자동으로 DB 연결을 시도하나 DB 정보가 없어 에러 발생
    
4. 원인 상정 및 검증
    
    DBConfig 설정 및 `R2dbcAutoConfiguration` 제외
    
    `@SpringBootApplication(exclude = {R2dbcAutoConfiguration.class})`
