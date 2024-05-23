package com.example.modoo.config;

//import com.example.modoo.jwt.CustomUserDetailsService;
import com.example.modoo.jwt.JwtFilter;
import com.example.modoo.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final TokenProvider tokenProvider;
    private final CustomAccessDeniedHandler accessDeniedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // REST API이므로 CSRF 보안을 사용하지 않음
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/SignUpPage").permitAll()
                        .requestMatchers("/api/signup").permitAll()
                        .requestMatchers("/api/signIn").permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(e -> e
                        .accessDeniedHandler(accessDeniedHandler)
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                )
                .addFilterBefore(new JwtFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
