package com.spring.security.custom.authentication.event.infrastructure.event.provider;

import com.spring.security.custom.authentication.event.infrastructure.event.custom.CustomAuthenticationFailureEvent;
import com.spring.security.custom.authentication.event.infrastructure.event.custom.CustomAuthenticationSuccessEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final ApplicationContext applicationContext;

    public CustomAuthenticationProvider(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(!authentication.getName().equals("user")){
            applicationContext.publishEvent(new CustomAuthenticationFailureEvent(authentication, new BadCredentialsException("BadCredentialsException")));
            throw new BadCredentialsException("BadCredentialsException");
        }

        UserDetails user = User.withUsername("user").password("{noop}1111").roles("USER").build();
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                user,
                user.getUsername(),
                user.getAuthorities()
        );

        // SecurityConfig 클래스에서 form의 successHandler에서 호출 안하고 여기서 하면 되는 것 아닌가?
        // 여기는 커스텀으로 호출하고 싶으면 하고 successHandler에서 필요한 이벤트 호출과 response 제어 또한 하면 될거라는 생각.
        applicationContext.publishEvent(new CustomAuthenticationSuccessEvent(authentication));
        return usernamePasswordAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
