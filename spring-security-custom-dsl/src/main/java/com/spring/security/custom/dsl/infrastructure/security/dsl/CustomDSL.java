package com.spring.security.custom.dsl.infrastructure.security.dsl;

import com.spring.security.custom.dsl.infrastructure.security.filter.CustomFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;

public class CustomDSL extends AbstractHttpConfigurer<CustomDSL, HttpSecurity> {

    private static CustomDSL customDSL;


    @Override
    public void init(HttpSecurity builder) throws Exception {
        super.init(builder);
    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        CustomFilter customFilter = new CustomFilter(true);
        builder.addFilterAfter(customFilter, SecurityContextHolderAwareRequestFilter.class);
    }

    public static CustomDSL getInstance(){
        if(customDSL == null){
            customDSL = new CustomDSL();
        }

        return customDSL;
    }


}
