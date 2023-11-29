package com.example.board.core.security;
//https://pygmalion0220.tistory.com/entry/Spring-Boot-Rest-API-%EC%97%90-Spring-Security-Form-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0

import com.example.board.core.error.exception.Exception401;
import com.example.board.core.error.exception.Exception403;
import com.example.board.core.utils.FilterResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Slf4j
@RequiredArgsConstructor
@Configuration // ** 현재 클래스를 (설정 클래스)로 설정
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();
    }

    public class CustomSecurityFilterManager extends AbstractHttpConfigurer<CustomSecurityFilterManager, HttpSecurity>{

        @Override
        public void configure(HttpSecurity httpSecurity) throws Exception {

            AuthenticationManager authenticationManager = httpSecurity.getSharedObject(
                    AuthenticationManager.class
            );

            httpSecurity.addFilter(new JwtAuthenticationFilter(authenticationManager));

            super.configure(httpSecurity);
        }
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http.headers().frameOptions().sameOrigin();

        http.cors().configurationSource(configurationSource());

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.formLogin().disable();

        http.httpBasic().disable();

        http.apply(new CustomSecurityFilterManager());

        http.exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
            log.warn("인증되지 않은 사용자가 자원에 접근하려 합니다 : " + authException.getMessage());
            FilterResponseUtils.unAuthorized(response, new Exception401("인증되지 않았습니다"));
        });

        http.exceptionHandling().accessDeniedHandler((request, response, accessDeniedException) -> {
            log.warn("권한이 없는 사용자가 자원에 접근하려 합니다 : " + accessDeniedException.getMessage());
            FilterResponseUtils.forbidden(response, new Exception403("권한이 없습니다"));
        });

        http.authorizeRequests(
                authorize -> authorize.antMatchers("/carts/**", "/options/**", "/orders/**").authenticated()
                        .antMatchers("/admin/**")
                        .access("hasRole('ADMIN')")
                        .anyRequest().permitAll()
        );

        return http.build();
    }

    public CorsConfigurationSource configurationSource() {
        CorsConfiguration corsConfigurationSource = new CorsConfiguration();
        corsConfigurationSource.addAllowedHeader("*");
        corsConfigurationSource.addAllowedMethod("*");
        corsConfigurationSource.addAllowedOriginPattern("*");
        corsConfigurationSource.setAllowCredentials(true);
        corsConfigurationSource.addExposedHeader("Authorization");

        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource
                = new UrlBasedCorsConfigurationSource();

        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfigurationSource);
        return urlBasedCorsConfigurationSource;
    }
}












