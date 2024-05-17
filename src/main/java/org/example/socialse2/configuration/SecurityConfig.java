package org.example.socialse2.configuration;

import org.example.socialse2.handler.CustomSuccessHandler;
import org.example.socialse2.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomSuccessHandler customSuccessHandler;

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomSuccessHandler customSuccessHandler,
                          CustomUserDetailsService customUserDetailsService) {
        this.customSuccessHandler = customSuccessHandler;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(request -> request.anyRequest().permitAll())
            .formLogin(form -> form.loginPage("/login")
                                   .loginProcessingUrl("/login")
                                   .successHandler(customSuccessHandler)
                                   .permitAll())
            .logout(logout -> logout.invalidateHttpSession(true)
                                    .clearAuthentication(true)
                                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                    .logoutSuccessUrl("/login?logout")
                                    .permitAll());
        return http.build();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
