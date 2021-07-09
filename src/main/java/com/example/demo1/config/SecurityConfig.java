package com.example.demo1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
			// 기존 java에 만들어져 있는 객체를 Bean으로 등록할 때
	@Bean	// 사용자가 만든 객체는 Component를 쓴다.
	public BCryptPasswordEncoder encodePwd() { // 비밀번호 인코더 생성
		return new BCryptPasswordEncoder();
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); // basic auth를 사용하기 위해 csrf 보호 기능 disable
		//권한설정 : /user/*에는 권한요구, 나머지는 전체 허용
		http.authorizeRequests()
			.antMatchers("/user/*").authenticated()
			.anyRequest().permitAll()
			.and() // 그리고, 로그인 페이지 설정
			.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/loginPro") // 액션
			.defaultSuccessUrl("/") // 성공시 이동하는 url
			.and()  // 그리고, 로그아웃 페이지 설정
			.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/")
			.invalidateHttpSession(true); // 로그아웃시 세션제거
			
	}
	
}
