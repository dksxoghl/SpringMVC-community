package com.taehi.springfirst.config;

//import com.taehi.springfirst.service.MemberService;
import com.taehi.springfirst.service.MemberService;
import com.taehi.springfirst.service.MemberServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@AllArgsConstructor
@EnableWebSecurity      //없어도?..
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private MemberService memberService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/css/**", "/resources/js/**", "/resources/ckeditor/**", "/img/**");      //해당경로파일 스프링이 무시하도록
//        web.ignoring().mvcMatchers("/favicon.ico");
//        web.ignoring().requestMatchers(PathRequest.toStaticResources())
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 페이지 권한 설정
                .antMatchers("/**/writeForm*","/**/deleteForm/*","/reply/*","/reply*").hasRole("USER")
                .antMatchers("/**").permitAll()
                .and() // 로그인 설정
                .formLogin()
                    .loginPage("/loginForm")
//                .loginProcessingUrl("/loginForm")
                    .defaultSuccessUrl("/hy")
                    .failureUrl("/loginForm?error=true&url=hy")
//                    .failureHandler(failureHandler())
                    .permitAll()
                 .and() // 로그아웃 설정
                .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                 .logoutSuccessUrl("/hy")
                 .invalidateHttpSession(true)
                 .and()
                .headers()   //보안헤더활성화시 브라우저가 더이상페이지 캐시하지않음.
                .and()
                .rememberMe();

//                .and().csrf().disable();

//                .and()
//                // 403 예외처리 핸들링  커스텀페이지필요
//                .exceptionHandling().accessDeniedPage("/user/denied");
    }
    @Bean
    public AuthenticationFailureHandler failureHandler(){
        return new CustomAuthFailureHandler();
    }
}
