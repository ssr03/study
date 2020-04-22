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

* 프로젝트 폴더 만들기

  ```bash
  $mkdir git-study
  $cd git-study/
  ```

* 버전 관리를 하려고 하는 디렉토리를 git에게 알려주기

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

* 버전관리 할 파일을 git에 추가

  ```bash
  $git add f1.txt
  ```

## 버전만들기(commit)

* 버전

  * 의미 있는 변화를 의미
  * 작업이 완결된 상태

* 저장소의 상태 확인

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

* 버전 확인

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

* 커밋이 된 결과가 저장되는 곳

## 변경 사항 확인하기(log&diff)



## 과거로 돌아가기(reset)

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

