package com.mcopue.sfconnector;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@Configuration
@PropertySource("classpath:app.yml")
public class AppConfig {
    @Bean
    public org.springframework.web.servlet.ViewResolver viewResolver() {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setPrefix("webapp/jsp/");
        bean.setSuffix(".jsp");
        return bean;
    }
}
