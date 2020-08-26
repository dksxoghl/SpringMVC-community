package com.taehi.springfirst.config;

//import com.taehi.springfirst.service.MemberService;
import com.taehi.springfirst.service.MemberServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    private MemberService memberService;
    private MemberServiceImpl memberServiceImpl;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberServiceImpl).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/css/**", "/resources/js/**", "/resources/ckeditor/**", "/img/**");      //해당경로파일 스프링이 무시하도록
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 페이지 권한 설정
                .antMatchers("/ja*").hasRole("USER")
                .antMatchers("/**").permitAll()
                .and() // 로그인 설정
                .formLogin()
                .loginPage("/loginForm")
                .defaultSuccessUrl("/hy")
                .permitAll()
                .and() // 로그아웃 설정
                .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                .logoutSuccessUrl("/hy")
                .invalidateHttpSession(true)
                .and().csrf().disable();

//                .and()
//                // 403 예외처리 핸들링
//                .exceptionHandling().accessDeniedPage("/user/denied");
    }
}
