# Security
Spring security는 스프링 기반의 애플리케이션을 위한 보안 솔루션을 제공

애플리케이션의 보안에서 중요한 두 가지 영역은 '인증'과 '인가'이다.<br>
웹에서 인증이란 해당 리소스에 대해서 작업을 수행할 수 있는 주체인지 확인하는 것이다.
예를 들면 커뮤니티에서 게시글을 보는 것은 로그인을 하지 않아도 되지만, 댓글을 작성하려면
로그인을 해야하는데 댓글을 작성하기 위해 로그인 인증 절차를 거쳐야 한다.

인가는 인증 과정 이후 일어난다. 관리자 페이지에 접근하는 URL을 입력했을 때 해당 URL은 
커뮤니티 의 관리자만 접근할 수 있어야 한다. 이때 접근하는 사용자가 해당 URL에 대해서 
인가된 회원인지 검사하는것이다.

### Spring security 설정
 1. WebSecurityConfigurerAdapter를 상속받는 클래스에 @EnableWebSecurity 어노테이션을 선언<br>(SpringSecurityFilterChain이 자동으로 포함된다. WebSecurityConfigurerAdapter를 상속받아 메소드오버라이딩을 통해 보안 설정을 커스터 마이징할 수 있다.)
 2. configure(HttpSecurity) 메소드를 이용해 Http에 대한 보안을 설정한다.<br>(페이지 권한 설정, 로그인 페이지 설정, 로그아웃 메소드 등 설정)
 3. PasswordEncoder() 메소드를 이용해 BCryptPasswordEncoder의 해시 함수를 이용하여 비밀번호를 암호화하여 저장한다.<br>(@Been 등록해서 사용한다.)

### Role enum 클래스
일반 유저인지 관리자인지 구분할 수 있는 역할을 담당하는 클래스를 만든다.

### CSRF(Cross Site Request Forgery)
Spring Security를 사용할 경우 기본적으로 CSRF를 방어하기 위해 모든 POST방식의 데이터 전송에는 CSRF 토큰 값이 있어야 한다.<br>
CSRF 토큰은 실제 서버에서 허용한 요청이 맞는지 확인하기 위한 토큰이다. 사용자의 세션에서 값을 저장하여 
요청마다 그 값을 포함하여 전송하면 서버에서 세션에 저장된 값과 요청 온 값이 일치하는지 확인하여 CSRF를 방어한다.
````
사이트간 위조 요청으로 사용자가 자신의 의지와 상관없이 해커가 의도한 대로
수정, 등록, 삭제 등의 행위를 웹사이트 요청하게 하는 공격을 말한다.
````

### UserDetailService
Spring Security에서 UserDetailService를 구현하고 있는 클레스를 통해 로그인 기능을 구현한다.
 * DB에서 회원 정보를 가져오는 역할을 담당
 * loadUserByUsername() 메소드가 존재하며, 회원 정보를 조회하여 사용자의 정보와 권한을 갖는 UserDetail 인터페이스를 반환

### UserDetail
Spring Security에서 회원의 정보를 담기 위해서 사용하는 인터페이스이다.<br>
Spring Security에서 제공하는 User 클래스를 사용하며 User 클래스틑 UserDetails 인터페이스를 구현하고 있는 클래스이다.

## 페이지 접근 권한 설정
AuthenticationEntryPoint 인터페이스를 구현하여 권한별 페이지 접근을 설정 할 수 있다.