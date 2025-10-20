package com.sam.personalBlog.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

import static com.sam.personalBlog.model.enums.Permission.*;
import static com.sam.personalBlog.model.enums.Role.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig {

        @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,PersistentTokenRepository repo) throws Exception
        {
            http
                    .csrf(ye -> ye.disable())

                    .authorizeHttpRequests(yeah -> yeah
                            .requestMatchers("/css/**").permitAll()
                            .requestMatchers("/welcome","/login").permitAll()
                            .requestMatchers("/test/**").permitAll()
                            .requestMatchers(HttpMethod.GET,"/blogs/**").hasAuthority(BLOGS_READ.getPermission())
                            .requestMatchers(HttpMethod.POST,"/blogs/**").hasAuthority(BLOGS_WRITE.getPermission())
                            .requestMatchers(HttpMethod.DELETE,"/blogs/**").hasAuthority(BLOGS_DELETE.getPermission())
                            .requestMatchers(HttpMethod.PUT,"/blogs/**").hasAuthority(BLOGS_UPDATE.getPermission())
                            .anyRequest().authenticated())
                    .formLogin(login ->
                            login
                                    .loginPage("/login")
                                    .defaultSuccessUrl("/welcome",true)
                                    .permitAll())
                    .rememberMe(rm ->
                            rm
                                    .tokenRepository(repo)
                                    .tokenValiditySeconds(100000)
                                    .key("super-secret-key")
                                    .rememberMeParameter("remember-me"));

            return http.build();
        }

        @Bean
        public UserDetailsService buildUsers(PasswordEncoder encoder)
        {
            UserDetails user1 = User.builder()
                    .username("sam")
                    .password(encoder.encode("password123"))
                    .roles("ADMIN")
                    .authorities(ADMIN.getGrantedAuthorities())
                    .build();
            UserDetails user2 = User.builder()
                    .username("greg")
                    .password(encoder.encode("password123"))
                    .roles("USER")
                    .authorities(USER.getGrantedAuthorities())
                    .build();

            return new InMemoryUserDetailsManager(user1,user2);
        }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(DataSource dataSource) {
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
     //   repo.setCreateTableOnStartup(true); // optionally auto-create table
        return repo;
    }


}
