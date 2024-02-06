# gongdae

## git commit message

Feat : 기능 추가   
Edit : 기능 수정   
Fix : 버그 수정   
Docs : 문서 수정   
Style : 스타일 수정 (코드 포맷팅, 세미콜론 누락 등 코드 자체 변경 x)   
Refactor : 코드 리펙토링   
Test : 테스트 코드, 리펙토링 테스트 코드 추가   
Chore : 빌드 업무 수정, 패키지 매니지 수정   
Etc : 기타   

## branching strategy
```
├── main
└── feature     
ex) 닉네임/server|client/feature/[띄어쓰기는-하이픈으로]
```

## tree
```
.
├── README.md
└── server
    ├── ...
    └── src
        ├── main
        │   ├── java
        │   │   └── com
        │   │       └── jy
        │   │           └── gongdae
        │   │               ├── GongdaeApplication.java
        │   │               ├── config
        │   │               ├── domain
        │   │               │   ├── BaseTimeEntity.java
        │   │               │   └── space
        │   │               │       ├── Space.java
        │   │               │       └── SpaceRepository.java
        │   │               ├── service
        │   │               │   └── space
        │   │               │       └── SpaceService.java
        │   │               └── web
        │   │                   ├── SpaceApiController.java
        │   │                   └── dto
        │   │                       └── SpaceSaveRequestDto.java
        │   └── resources
        │       ├── ...
        └── test
            └── ...
```
