package com.taehi.springfirst.config;

import com.taehi.springfirst.persistence.ReplyDAO;
import com.taehi.springfirst.persistence.ReplyDAOImpl;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.util.Locale;

@Configuration
public class Config implements WebMvcConfigurer {

    @Bean
    public MessageSource messageSource(){
        Locale.setDefault(Locale.KOREA); // 위치 한국으로 설정
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setDefaultEncoding("UTF-8"); // 인코딩 설정
        messageSource.setBasenames("classpath:message/security_message", "classpath:org/springframework/security/messages"); // 커스텀한 properties 파일, security properties 파일 순서대로 설정
        return messageSource;
    }

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**")
                .addResourceLocations("/resources/css/")
                .setCachePeriod(20);
        registry.addResourceHandler("/ckeditor/**")
                .addResourceLocations("/resources/ckeditor/")
                .setCachePeriod(20);
        registry.addResourceHandler("/js/**")
                .addResourceLocations("/resources/js/")
                .setCachePeriod(20);
    }


//    @Bean
//    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
    //테스트용
//    @Bean
//    public ReplyDAO replyDAO(){
//        ReplyDAO replyDAO = new ReplyDAOImpl(dataSource());
//        return replyDAO;
//    }

    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername("postgres");
        dataSource.setPassword("1q2w3e4r5t");
//        dataSource.setPassword("password");
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setMinimumIdle(2);
        dataSource.setMaximumPoolSize(5);
        return dataSource;
    }
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/ckeditor/**")
//                .addResourceLocations("classpath:/ckeditor/") // '/'으로 끝나도록
//                .setCachePeriod(20);
//
//    }

}
