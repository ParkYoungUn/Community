package com.yi.community.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        		// .csrf().disable() // 보안 해제(주의! 보안이 취약해짐)
                .authorizeRequests()
                    .antMatchers("/", "/account/register", "/css/**").permitAll() // permitAll : 누구나 접근 할 수 있다.
                    .anyRequest().authenticated()	// anyRequest : 그 밖의 , authenticated: 로그인 되어있어야 한다.
                    .and()	// 끝
                .formLogin()
                    .loginPage("/account/login")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();
    }
    
//    @Bean		/** 임시적인 유저를 만드는 곳 **/
//	@Override
//	public UserDetailsService userDetailsService() {
//		UserDetails user =
//			 User.withDefaultPasswordEncoder()
//				.username("user")
//				.password("password")
//				.roles("USER")
//				.build();
//
//		return new InMemoryUserDetailsManager(user);
//	}

//    @OneToOne: user - ex) user_detail
//    @OneToMany: user - ex) board
//    @ManyToOne: ex) board - user
//    @ManyToMany: ex) user -role
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("select username,password,enabled " // 인증
                        + "from user "
                        + "where username = ?")
                .authoritiesByUsernameQuery("select u.username,r.name " // 권한
                        + "from user_role ur inner join user u on ur.user_id = u.id "
                        + "inner join role r on ur.role_id = r.id "
                        + "where u.username = ?");
    }

//    Authentication 로그인(인증)
//    Authroization 권한
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}