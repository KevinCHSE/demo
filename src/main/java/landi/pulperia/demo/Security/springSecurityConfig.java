package landi.pulperia.demo.Security;

import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import landi.pulperia.demo.Security.Filter.JWTAuthenticationFilter;
import landi.pulperia.demo.Security.Filter.JwtValidationFilter;

@Configuration 
public class springSecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration; 
    
    public springSecurityConfig(AuthenticationConfiguration authenticationConfiguration) {
        this.authenticationConfiguration = authenticationConfiguration;
    }

    @Bean 
    AuthenticationManager authenticationManager(){
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean 
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Bean 
    SecurityFilterChain filterChain(HttpSecurity http){
        return http.authorizeHttpRequests(auth->
            auth
            .anyRequest().permitAll())
            .addFilter(new JWTAuthenticationFilter(authenticationManager()))
            .addFilterBefore(new JwtValidationFilter(authenticationManager()),BasicAuthenticationFilter.class)
            .csrf(config->config.disable())
            .cors(cors->cors.configurationSource(corsConfigurationSource()))
            .sessionManagement(manage->manage.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .build();
    }

    @Bean 
        CorsConfigurationSource corsConfigurationSource(){
            CorsConfiguration config = new  CorsConfiguration();
            config.setAllowedOriginPatterns(List.of("https://landi-angular.vercel.app"));
            config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
            config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
      
            config.setAllowCredentials(true);
          
            org.springframework.web.cors.UrlBasedCorsConfigurationSource source= new org.springframework.web.cors.UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", config);
            return source;
        
        }

        @Bean 
        FilterRegistrationBean<CorsFilter>corsFilter(){
            FilterRegistrationBean<CorsFilter> corsBean= 
                new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource()));
            corsBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
            return corsBean;
        } 


}
