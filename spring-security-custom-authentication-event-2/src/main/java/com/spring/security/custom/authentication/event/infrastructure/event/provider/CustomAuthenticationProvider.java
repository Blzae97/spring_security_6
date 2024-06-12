package com.spring.security.custom.authentication.event.infrastructure.event.provider;

import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final AuthenticationEventPublisher eventPublisher;

    public CustomAuthenticationProvider(AuthenticationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!authentication.getName().equals("user")) {
            // 3번씩 호출 되는데 이유가 뭔지 모르겠음. filter chain 중에 이 Provider를 쓰는게 있나?
            eventPublisher.publishAuthenticationFailure(new BadCredentialsException("BadCredentialsException"), authentication);
            throw new BadCredentialsException("BadCredentialsException");
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
