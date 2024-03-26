package kr.basic.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web)->{
            web.ignoring().requestMatchers(new String[]{"/favicon.ico","/resources/**","/error"});
        };
    }
    @Bean
    AuthenticationFailureHandler customAuthFailureHandler(){
        return new CustomAuthFailureHandler();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(
                authz -> authz
                        .requestMatchers("/user/**").authenticated() // 인증이 되면 누구나 들어올수있음
                        .requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN") // role이 메니저나 어드민만 들어올수있음
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                        .anyRequest().permitAll()


        ).formLogin(
                form -> {
                    form.loginPage("/loginForm")   // 우리가 만든 로그인페이지로 자동 인터셉트됨
                            .loginProcessingUrl("/login")
                            .failureHandler(customAuthFailureHandler())
                            .defaultSuccessUrl("/", true);  // 로그인 성공하면 돌아올 페이지

                }
        );

        return http.build();
    }
}
