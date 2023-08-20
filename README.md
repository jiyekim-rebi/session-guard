# session-guard
중복로그인 방지 연습 프로젝트

<hr/>

### 1. 목표
* 같은 아이디로 기기에 로그인
* 로그아웃 하지 않은 상태에서 다른 기기에서 같은 아이디로 로그인
* 기존에 로그인 하고 있던 기기는 실시간으로 로그아웃 처리 (다른 기기에서 접속했으므로 로그아웃됩니다 팝업처리)
* 사용 가능한 로그인(토큰)인지 검증하는 로직 필요
    * 기존에 로그인한 기기지만, 창을 닫거나 네트워크 이상으로 통신이 되지 않을 수도 있으므로, 계정 정보가 저장되어 있다면 backend로 사용가능한 토큰 정보인지 확인이 필요함
* 접속한 ip와 접속 디바이스 판단 필요
    * 같은 ip 내 다른 디바이스 접속이여도 기 로그인된 정보는 사용불가 처리
    * 이후 최대 n개까지 로그인 허용 기능으로 확대될 수도 있음

### 2. 구성
> Vue.js 3 (로그인/강제 로그아웃 테스트용 웹)
>> DNS : Cloud flare (https://www.cloudflare.com/ko-kr) <br>
>> WEB : Netilfy (https://www.netlify.com)

> API
> - Java(JPA)? Go? 
> - 상황에 따라 redis를 써야할수도 있을 것 같은데 고민중
> - websocket
>> Horoku (https://www.heroku.com)

> Oracle (계정정보 저장)
>> Oracle Cloud Infrastructure freetier (https://www.oracle.com/kr/cloud/free)
