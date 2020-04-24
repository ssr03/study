# 지옥에서 온 Git

## 수업 소개

* 버전 관리 시스템(Version Control System)
  * 파일 이름 변경하지 않고 버전을 관리(변경 사항 관리)
  * 소스코드 백업, 이전 상태로 돌아갈 수 있음, 협업(Backup, Recovery, Collaboration)
* Version Control System
  * CVS-예전이 많이 사용
  * SVN
  * GIT-오늘날 가장 많이 사용

## 설치-Window

> git download: http://git-scm.com

* git bash 실행
  * linux계열 명령어를 window에서 사용할 수 있도록 함

## git init

* 프로젝트 폴더 만들기

  ```bash
  $mkdir git-study
  $cd git-study/
  ```

* 버전 관리를 하려고 하는 디렉토리를 git에게 알려주기

  ```bash
  // 현재 디렉토리 버전관리
  $git init 
  
  $git -al
  ```

  * `.git`디렉토리가 만들어졌는지 확인
    * 버전정보 저장되는 장소

## git add

* f1.txt 파일 생성

  ```bash
  $ vim f1.txt
  ```

  * vim이라는 프로그램으로 f1.txt를 편집

    ```bash
    // i로 insert 후에 입력
    source : 1
    ```

    * 입력을 다 한 후에는 esc
    * `:wq`입력하여 저장 후 종료

* f1.txt파일 내용 보기

  ```bash
  $cat f1.txt
  ```

* git에 의해 관리되는 파일들의 상태 확인

  ```bash
  $git status
  ```

* 버전관리 할 파일을 git에 추가

  ```bash
  $git add f1.txt
  ```

## 버전만들기(commit)

* 버전

  * 의미 있는 변화를 의미
  * 작업이 완결된 상태

* 저장소의 상태 확인

  ```bash
  $git status
  ```

* git을 처음 사용하는 경우,  버전을 작성한 사용자 이름 셋팅

  ```bash
  $git config --global user.name ssr03
  $git config --global user.email ssr03@gmail.com
  ```

  * 한번만 해주면 됨

* commit

  ```bash
  $git commit
  ```

  * 현재 버전의 메세지(commit 메세지) 적기
  * `i`를 눌러서 입력-커밋 메세지 입력-esc-`:wq`입력하여 저장후 종료

* 버전 확인

  ```bash
  $git log
  ```

* 추가 실습

  ```bash
  $vim f1.txt
  ```

  * 내용을 `source : 2`로 변경 

  ```bash
  #현재 상태 확인
  $git status
  
  #최초 추적, 파일 수정 후 버전을 만들기 전에도 add 해야 함
  $git add f1.txt
  
  #commit메세지를 2로 해서 저장
  $git commit 
  
  $git log
  ```

## git stage area

* f1.txt, f2.txt 모두 수정

  ```bash
  # 파일 두개 각각 내용 수정
  $vim f1.txt
  $vim f2.txt
  
  $git status
  
  # 커밋 하고자 하는 파일만 git에 추가(add를 할 파일만 commit)
  $git add f1.txt
  $git commit
  ```

  * 커밋 대기 상태: stage area
  * git add를 하면 stage에 올라가게 됨->commit->stage위에 있는 파일들이 버전이 됨

### stage

* 커밋 대기를 하고 있는 파일들이 가는 곳

### repository

* 커밋이 된 결과가 저장되는 곳

## 변경 사항 확인하기(log&diff)

* 차이점 확인

  ```bash
  $git log
  ```
  * 지금까지의 역사 보기

* 커밋 메세지 보기
  
  ```bash
$git log -p
   ```
   
   *  소스와 소스 사이의 차이점 볼 수 있음

   * 결과 화면

     <img src="..\assets\git log.PNG" alt="git log화면" style="zoom: 130%;" />
   
     * `--- /dev/null`은 이전 버전 내용
   
       * `/dev/null`은 이전 버전에선 파일이 없음을 의미
   * `+++ /b/f1.txt`는 현재 버전(1)의 내용
   
* commit 아이디-commit메세지가 가르키는 고유한 주소

  ```bash
  $git log
  
  # 해당 commit이전의 메세지만 보기
  $git log 2d8b70c22ccbc26fe28fbc1fb59c656102b66d70
  ```
  
  * 각각의 commit은 자신의 고유한 아이디를 갖게 됨 
    * ex) `commit 2d8b70c22ccbc26fe28fbc1fb59c656102b66d70`
  
* commit의 차이점

   ```bash
   git diff 48c8781c6d51d950c463f26dee0163fab4464c80..8904d7d07aa0c577485b9fcb4a46a04bfd9e0812
   
   # commit전 현재 변경 사항 파악
   git diff
   ```

   * 소스상의 차이점 보여줌
   * 버전과 버전 사이 소스코드 차이 볼 수 있음

   * `git  diff`: 커밋 전에 이전 커밋과의 차이점

## 과거로 돌아가기(reset)

* commit을 취소하는 명령

* 현재의 log를 취소해서 과거로 돌아가기

  1. reset
  2. revert
     * 커밋을 취소하면서 새로운 버전 생성

* 이전 상태로 돌아가기

  ```bash
  $git log
  $git reset 656d5ccab02a1741498abab8399bd393c4a22d2c --hard
  
  $git log
  ```

  * 초기화한 버전의 상태로 돌아감
  * 이후의 버전은 삭제

* 가능한 어떠한 정보도 삭제하지 않는게 좋음

  * reset의 경우 복구 가능(남아있음)
  * 공유한 이후에는 reset해서는 안됨
  * 본인의 컴퓨터에서만 reset작업하기

## git의 원리소개

## 분석도구 gistory

## git add

## objects 파일명의 원리

## commit의 원리

## status의 원리

## branch소개

## branch 만들기

## branch 수련

## branch 추돌해결

## stash

## 원리_branch

## 원리_reset checkout

## 원리_working copy&index&repository

## 원리_merge&conflic

## 원리_3 way merge

## 원격 저장소 소개

## 원격 저장소 생성

## github소개

## 원격 저장소 만들기(Github)

## 동기화 방법(Github)

## ssh를 이용해서 로그인없이 원격저장소 사용하기

## 자기 서버에 원격 자장소 만들기(My server)

## push&pull(My server)

## 자동 로그인(My server)

## 원격 저장소의 원리

## pull vs fetch의 원리

## Rebase

## Git을 이용한 프로젝트의 흐름(Git Flow)

