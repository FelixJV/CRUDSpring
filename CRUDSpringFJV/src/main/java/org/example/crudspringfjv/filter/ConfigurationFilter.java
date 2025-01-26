package org.example.crudspringfjv.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationFilter {

    @Bean
    public FilterRegistrationBean<FiltroLogin> headerValidatorFilter() {
        FilterRegistrationBean<FiltroLogin> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new FiltroLogin());
        registrationBean.addUrlPatterns("/l");
        return registrationBean;
    }
}
