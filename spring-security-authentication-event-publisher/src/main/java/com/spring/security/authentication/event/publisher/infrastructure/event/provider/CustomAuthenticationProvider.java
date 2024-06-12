package com.spring.security.authentication.event.publisher.infrastructure.event.provider;

import com.spring.security.authentication.event.publisher.infrastructure.exception.CustomException;
import com.spring.security.authentication.event.publisher.infrastructure.exception.DefaultAuthenticationException;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final AuthenticationEventPublisher eventPublisher;

    public CustomAuthenticationProvider(AuthenticationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        if(name.equals("admin")){
            eventPublisher.publishAuthenticationFailure(new CustomException("CustomException"), authentication);
            throw new CustomException("CustomException");
        }

        if(name.equals("db")){
            eventPublisher.publishAuthenticationFailure(new DefaultAuthenticationException("DefaultAuthenticationException"), authentication);
            throw new DefaultAuthenticationException("DefaultAuthenticationException");
        }


        UserDetails user = User.withUsername("user").password("{noop}1111").roles("USER").build();
        return new UsernamePasswordAuthenticationToken(
                user,
                user.getUsername(),
                user.getAuthorities()
        );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
