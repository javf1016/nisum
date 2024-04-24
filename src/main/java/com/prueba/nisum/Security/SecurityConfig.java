package com.prueba.nisum.Security;

import com.prueba.nisum.Security.Jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtFilter jwtFilter;

    @Autowired
    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/users/create", "/h2/**", "/favicon.ico", "/swagger-ui/**",
                        "/swagger-resources/**",
                 "/swagger-ui.html",
                 "/v2/api-docs",
                 "/webjars/**",
                 "/view/**").permitAll() // Permitir acceso sin autenticación a /users
                .anyRequest().authenticated() // Requiere autenticación para todas las demás rutas
                .and()
                .headers().frameOptions().sameOrigin() // Permitir el uso de la consola H2 en un iframe del mismo origen
                .and()
                .sessionManagement().disable();

        // Agregar el filtro JWT después del filtro de autenticación de Spring Security
        http.addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
