package com.fincatto.springvaadin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import java.util.Arrays;
import java.util.Collection;

@Configuration
@EnableWebSecurity
public class SpringVaadinSecurity extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // CSRF handled by Vaadin
                .csrf().disable()
                // configura a tela de login
                .exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login")).accessDeniedPage("/accessDenied").and().authorizeRequests()
                // allow Vaadin URLs without authentication
                .regexMatchers("/frontend/.*", "/VAADIN/.*", "/login.*", "/accessDenied.*").permitAll().regexMatchers(HttpMethod.POST, "/\\?v-r=.*").permitAll()
                // deny other URLs until authenticated
                .antMatchers("/**").fullyAuthenticated();
                //.antMatchers("/**").permitAll();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return Arrays.asList(new SimpleGrantedAuthority("user"), new SimpleGrantedAuthority("admin"));
            }

            @Override
            public String getPassword() {
                return "teste";
            }

            @Override
            public String getUsername() {
                return "diego@fincatto.com";
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        });
    }
}