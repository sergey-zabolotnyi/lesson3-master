package de.telran.lesson3.config_layer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // x -> x.disable()
                .authorizeHttpRequests(x -> x
                        .requestMatchers(HttpMethod.GET, "/product", "/customer").permitAll()
                        .requestMatchers(HttpMethod.GET, "/product/{id}","customer/add/{customerId}/{productId}" +
                                "customer/delete/{customerId}/{productId}", "customer/clear/{id}")
                        .hasAnyRole("USER", "MANAGER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/customer/{id}","/customer/count", "/product/count",
                                "product/total", "product/average", "customer/total/{id}",
                                "customer/average/{id}").hasAnyRole("MANAGER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/customer", "/product").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/customer/delete/{id}", "/customer/delete/{name}",
                                "/product/delete/{id}", "/product/delete/{name}").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
