package com.tvd.SpringBoot.SecurityConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	/* @Autowired
	   private AccessDeniedHandler accessDeniedHandler;*/
	
	 
	 @Autowired
	 private AuthenticationSuccessHandler filter;
	 
	 @Autowired
	 private CustomAuthenticationProvider authenticationProvider;
	
	// Authentication : User --> Roles
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		/*auth.inMemoryAuthentication().passwordEncoder(org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance()).withUser("user1").password("secret1")
				.roles("USER").and().withUser("admin1").password("secret1")
				.roles("USER", "ADMIN");*/
		
		auth.authenticationProvider(authenticationProvider);
	/*	auth.inMemoryAuthentication()
        .withUser("admin")
        .password("admin")
        .roles("USER");*/
	}

	// Authorization : Role -> Access
	protected void configure(HttpSecurity http) throws Exception {
		/*http.authorizeRequests()
		.antMatchers("/", "/home", "/about").authenticated()
		.antMatchers("/user*")
				.hasRole("USER").antMatchers("/admin").hasRole("ADMIN")
				.and()
				.formLogin()
					.loginPage("/login").successHandler(filter).failureUrl("/login?error=true")
					.and().logout()
					.logoutSuccessUrl("/logout")
					.logoutUrl("/logout")
					.deleteCookies("JSESSIONID")
					 .invalidateHttpSession(true)
					.and()
	                .sessionManagement()
	                .invalidSessionUrl("/logout").and()
				.csrf().disable().headers().frameOptions().disable()
				.and()
                .exceptionHandling().accessDeniedPage("/403");*/
		
		http.authorizeRequests().antMatchers("/home","/","/contact","/service","/about").authenticated()
		.antMatchers("/login").permitAll()
				.and()
				.formLogin().loginPage("/login").successHandler(filter)
				.failureUrl("/login?error=true")
				.and()
				.logout()
				.logoutUrl("/logout")
				.deleteCookies("auth_code", "JSESSIONID")
				 .invalidateHttpSession(true)
				.and()
                .sessionManagement()
                .invalidSessionUrl("/logout")
				 .and()
				.exceptionHandling().accessDeniedPage("/403").and().csrf().disable();
		
		
		/*http.authorizeRequests().antMatchers("/home","/","/contact","/service","/about").authenticated()
		.antMatchers("/login").permitAll()
				.and()
				.formLogin().loginPage("/login").successHandler(filter)
				.failureUrl("/login?error=true");
		
		http.logout()
        .logoutUrl("/logout")
        .logoutSuccessUrl("/")
        .invalidateHttpSession( true )
        .deleteCookies("JSESSIONID");
		
		http.sessionManagement()
        .sessionFixation()
        .newSession();
		
		http.csrf().disable();*/
		
	}

			@Override
			public void configure(WebSecurity web) throws Exception {
				  web.ignoring()
		          .antMatchers("/css/**")
		          .antMatchers("/js/**")
		          .antMatchers("/images/**");
			}
	
}