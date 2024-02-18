1번 조합: `@RequestBody` SpaceCreateDto, `@RequestPart` MultipartFile

```2024-02-16T16:12:17.148+09:00 WARN 5522 --- [nio-8080-exec-2] .w.s.m.s.DefaultHandlerExceptionResolver : Resolved [org.springframework.web.HttpMediaTypeNotSupportedException: Content-Type 'multipart/form-data;boundary=--------------------------771750305634248522649202;charset=UTF-8' is not supported]```

2번 조합: `@RequestPart` SpaceCreateDto, `@RequestPart` MultipartFile

```2024-02-16T16:13:57.259+09:00 WARN 6308 --- [nio-8080-exec-1] .w.s.m.s.DefaultHandlerExceptionResolver : Resolved [org.springframework.http.converter.HttpMessageNotReadableException: JSON parse error: Invalid UTF-8 start byte 0xbd]```

`@ModelAttribute`

3번 조합: `@ModelAttribute` SpaceCreateDto, `@ModelAttribute` MultipartFile

```2024-02-16T16:14:39.364+09:00 ERROR 6615 --- [nio-8080-exec-2] o.a.c.c.C.[.[.[/].[dispatcherServlet] : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed: org.springframework.http.converter.HttpMessageNotWritableException: Could not write JSON: Infinite recursion (StackOverflowError)] with root cause```

<img width="928" alt="스크린샷 2024-02-18 오전 1 49 29" src="https://github.com/hyetae/gongdae/assets/86393948/205a8d8f-35e3-49d4-93c8-08b4474c7bce">

<img width="928" alt="스크린샷 2024-02-18 오전 1 49 20" src="https://github.com/hyetae/gongdae/assets/86393948/4670d36d-3aa8-443a-8ed0-31a8bfce30a8">

1. 상황 파악
    
    Space create 시, 이미지는 업로드가 되나 text 데이터(json)를 받아오지 못함
    
2. 문제 발생 출처 확인
    
    적절한 controller annotation 사용을 요구됨
    
3. 문제 발생 조건 파악
    
    `@RequestBody`
    
    application/json을 주고받을때 주로 사용함. multipart/form-data이 포함되는 경우는 사용 불가
    
    `@RequestPart`
    
    @RequestBody + multipart/form-data인 경우에 사용.
    
    RequestBody와 RequestPart는 HttpMessageConverter에 의해 동작하므로 Setter 없이 Object 생성됨.
    
    `@RequestParam`
    
    1개의 HTTP 파라미터를 받을 때 사용.
    multipart/form-data을 받아야 되는 경우에 사용 가능.
    기본 설정으로 필요 여부가 필수로 되어있음.
    
    `@ModelAttribute`
    
    @RequestParam과 기능은 유사하나 **필드 내부와 1:1로 값이 Setter나 Constructor를 통해 매핑**되기 때문에 DTO의 필드에 접근할 수 있는 적절한 수단이 필요함.
    
    💡Controller에서 어노테이션 생략시 **@ModelAttribute가 default**이다.
    
4. 원인 상정 및 검증
    
    Controller에서 `@ModelAttribute` 어노테이션 단일 사용 및 SpaceCreateDto에서 List<Images>를 List<MultipartFile>로 수정함. 애초부터 SpaceCreateDto로 데이터를 주고받는데 어노테이션을 json 따로 이미지 따로 해서 받으려고 한 게 웃기다. 
    

참고: https://middleearth.tistory.com/35
