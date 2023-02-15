package com.example.shopping_mall.config;

import com.example.shopping_mall.service.MemberService;
import com.example.shopping_mall.service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MemberServiceImpl memberService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/members/login")        // 로그인 페이지 url을 설정
                .defaultSuccessUrl("/")             // 로그인 성공 시 이동할 URL을 설정
                .usernameParameter("email")         // 로그인 시 사용할 파라미터 이름으로 email을 지정
                .failureUrl("/members/login/error")     // 로그인 실패 시 이동할 URL 설정
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout")) // 로그아웃 URL을 설정
                .logoutSuccessUrl("/");             // 로그아웃 성공 시 이동할 URL 설정

        http.authorizeRequests()
                // premitAll()을 통해 모든 사용자가 인증없이 해당 경로에 접근할 수 있도록 설정
                .mvcMatchers("/", "/members/**", "/item/**", "images/**").permitAll()
                .mvcMatchers("/admin/**").hasRole("ADMIN")
                // 위에서 설정해준 경로를 제외한 나머지 경로들은 모두 인증을 요구하도록 한다.
                .anyRequest().authenticated();

        http.exceptionHandling()
                // 인증되지 않은 사용자가 리소스에 접근하였을 때 수행되는 핸들러를 등록
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 아래 경로에 있는 파일들은 인증을 무시하도록 설정
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Spring Security에서 인증은 AuthenticationManager를 통해 이루어지며 AuthenticationManagerBuilder가
    // AuthenticationManager를 생성합니다. userDetailService를 수현하고 있는 객체로
    // memberServiceImpl을 지정해주며, 비밀번호 암호화를 위해 passwordEncoder를 지정해준다.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService)
                .passwordEncoder(passwordEncoder());
    }
}
