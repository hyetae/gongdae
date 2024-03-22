# 테스트 코드

작성한 코드나 비즈니스 로직을 테스트 위한 코드

### 테스트 코드를 왜 작성해야 하는가?

---

1. 개발 과정에서 문제를 미리 발견할 수 있다
2. 리팩토링의 리스크가 줄어든다
3. 애플리케이션을 가동해 테스트하는 것보다 빠른 진행이 가능하다
4. 하나의 명세 문서로서의 기능을 수행한다

### 단위 테스트 vs 통합 테스트

---

`단위 테스트`: 메서드 단위로 테스트. 메서드를 호출해 의도한 결과가 나오는지 확인. 적은 테스트 비용으로 빠른 피드백 가능.

`통합 테스트`: 여러 모듈을 함께 테스트해서 정상적인 로직 수행이 가능한지 확인. 단위 테스트와는 달리 데이터베이스나 네트워크 같은 외부 요인들을 포함해 테스트를 진행해 애플리케이션이 온전히 동작하는지 테스트. ⇒ 모듈을 통합하는 과정에서의 호환성 등을 포함해 애플리케이션이 정상적으로 동작하는지 확인. 수행 시마다 모든 컴포넌트가 동작하므로 테스트 비용이 커진다.

### 테스트 전략

---

**Given-When-Then 패턴**

`Given`: 테스트를 수행하기 전에 필요한 환경을 설정하는 단계. 변수를 정의하거나 Mock 객체를 통해 특정 상황에 대한 행동 정의.

`When`: 테스트의 목적을 보여 주는 단계. 실제 테스트 코드가 포함되며, 테스트를 통한 결괏값을 가져옴.

`Then`: 테스트의 결과를 검증하는 단계. When 단계에서 나온 결괏값을 검증. 

**F.I.R.S.T**

`Fast`: 테스트는 빠르게 수행되어야 함. 목적을 단순하게 설정하거나 단위 테스트를 작성해 테스트 속도를 높임.

`Isolated`: 하나의 대상에 대해서만 수행되어야 함. 외부 소스 사용 시 외부 요인으로 테스트가 수행되지 않을 수 있음.

`Repeatable`: 어떤 환경에서도 반복 가능해야 함. 개발 환경이나 네트워크 연결 여부에 영향을 받으면 안 됨.

`Self-Validating`: 테스트가 성공했는지 실패했는지 확인할 수 있어야 함. 결괏값과 기댓값을 개발자가 직접 확인하면 안 됨.

`Timely`: 테스트하려는 애플리케이션 코드를 구현하기 전에 완성돼야 함. *테스트 주도 개발 기반**이 아니라면 생략 가능.

테스트 주도 개발(TDD; Test-Driven Development): 애자일 방법론 중 하나

### JUnit

---

JAVA에서 사용되는 대표적인 테스트 프레임워크로서 테스트를 위한 도구 제공. 어노테이션 기반으로 간편하게 테스트 코드 작성 가능. 단정문(assert)을 통해 테스트 케이스의 기댓값이 정상적으로 도출되었는지 검토. JUnit 5는 크게 Jupiter, Platform, Vintage의 세 모듈로 구성됨.

### 생명주기

---

생명주기와 관련되어 테스트 순서에 관여하는 어노테이션

`@Test`: 테스트 코드를 포함한 메서드 정의

`@BeforeAll`: 테스트를 시작하기 전에 호출되는 메서드 정의

`@BeforeEach`: 각 테스트 메서드가 실행되기 전에 동작하는 메서드 정의

`@AfterAll`: 테스트를 종료하면서 호출되는 메서드 정의

`@AfterEach`: 각 테스트 메서드가 종료되면서 호출되는 메서드 정의

### 예제

---

1. **assertThat**
    - assertThat(*테스트 타겟*).메서드().메서드()….;
    - 각 메서드에 대해 *테스트 타겟*을 검증
    
    ```java
    @Test
    void example_assertThat() {
    		...
    		**assertThat**("Hello, world! Nice to meet you.")
    			.isNotEmpty()
    			.contains("Nice")
    			.doesNotContain("ZZZ")
    			.startsWith("Hell")
    			.endsWith("u.")
    			.isEqualTo("Hello, world! Nice to meet you.");
    }
    ```
    
    더 알아보기 ⬇️
    
    JUnit vs assertj 
    
    [[Java] JUnit의 assertThat보다 assertj의 assertThat을 써야하는 이유](https://jwkim96.tistory.com/168)
    

1. **@DataJpaTest**
    
    JPA에 관련된 요소들만 테스트하기 위한 어노테이션
    
    메모리 상에 내부 데이터베이스를 생성한 후 `@Entity` 클래스들을 등록하고 JPA Repository 설정들을 해 준다. 각 테스트마다 테스트가 완료되면 관련한 설정들은 롤백된다.
    
    ```java
    @DataJpaTest
    void example_DataJpaTest() {
    	...
    ```
    
    더 알아보기 ⬇️
    
    @DataJapTest
    
    [[spring test] 2. Spring Data Jpa Test](https://goodteacher.tistory.com/655)
    
    @SpringBootTest vs @DataJapTest
    
    [@SpringBootTest / @DataJpaTest 차이점 과 JPA 영속성 컨텍스트](https://cobbybb.tistory.com/23)
    

1. **Mokito**
    - 단위 테스트를 위해 모의 객체를 생성하고 관리하는 데 사용
    - 주로 단일 컴포넌트의 동작을 테스트하는 데 사용
    - 클래스 내의 개별 메서드나 함수, 서로 다른 클래스 또는 컴포넌트 간의 상호작용, 객체들 간의 협업 등을 테스트
    
    `Mock`: 테스트에 필요한 객체의 모의(가짜) 객체를 생성
    
    `Stub`: 모의 객체의 메서드 호출에 대한 ‘예상 동작’을 정의 ex) when().then()
    
    `Verify`: 모의 객체에 대해 특정 메서드가 호출되고 예상된 인자와 함께 호출되었는지 검증
    
    ```java
    @Test
    void example_MokitoTest() {
    		List<String> mockList = Mockito.mock(List.class);
    		Mockito.when(mockList.size()).thenReturn(5);
    		Mockito.verify(mockList).add("item");
    		...
    ```
    
    더 알아보기 ⬇️
    
    Mockito 테스트 흐름
    
    [[Java] Spring Boot Mockito 이해하기 : 테스트 흐름 및 사용예시](https://adjh54.tistory.com/346)
    
    Mockito vs BDDMockito
    
    [Mockito와 BDDMockito는 뭐가 다를까?](https://velog.io/@lxxjn0/Mockito와-BDDMockito는-뭐가-다를까)
    
2. **MockMVC**
    - 컨트롤러의 엔드포인트를 호출하여 HTTP 클라이언트의 요청을 모방하고 응답 확인
    
    ```java
    @WebMvcTest(SpaceController.class)
    class SpaceControllerTest {
    
        @Autowired
        private MockMvc mvc;
    
        @MockBean
        SpaceServiceImpl spaceService;
    
        @Test
        @DisplayName("Space 데이터 생성 테스트")
        void createSpaceTest() throws Exception {
    
            MockMultipartFile imageFile = new MockMultipartFile("images", "test-image.jpg",
                    MediaType.IMAGE_JPEG_VALUE, "test-image".getBytes());
    
            SpaceCreateDto spaceCreateDto = new SpaceCreateDto();
           ...
           
            mvc.perform(
    				        post("/api/v1/space")
                            .content(spaceCreateDto.toString())
                            .contentType(MediaType.MULTIPART_FORM_DATA))
                    .andExpect(status().isOk())
                    .andDo(print());
        ...
    ```
    
    더 알아보기 ⬇️
    
    MockMVC 
    
    [[Java] Spring Boot MockMvc 이해하기 : 테스트 흐름 및 사용예제](https://adjh54.tistory.com/347)
    
    @Mock vs @MockBean
    
    [[SpringBoot] @Mock, @MockBean 차이가 뭘까?](https://blusky10.tistory.com/entry/SpringBoot-Mock-MockBean-차이가-뭘까)