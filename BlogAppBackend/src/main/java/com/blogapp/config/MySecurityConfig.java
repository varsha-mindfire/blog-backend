package com.blogapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.blogapp.services.CustomUserDetailService;

@Configuration
@EnableWebSecurity //main annotation which enables web security module
@EnableGlobalMethodSecurity(
		prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter{ //provides all default security configuration
	
	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Autowired
	private JwtAuthenticationEntryPoint entryPoint;
	
	@Bean
	public JwtAuthenticationFilter authenticationJwtTokenFilter() {
		return new JwtAuthenticationFilter();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailService);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception
	{
		return super.authenticationManager();
	}
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(entryPoint)
//		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//		.and().authorizeRequests().antMatchers("/api/auth/**").permitAll()
//		.antMatchers("/api/blogs/**").fullyAuthenticated().antMatchers("/api/blogs/").fullyAuthenticated()
//		.antMatchers("/v2/api-docs",
//				"/configuration/ui",
//				"/swagger-resources/**",
//				"/configuration/security",
//				"/swagger-ui.html",
//				"/webjars/**")
//		.permitAll()
//		.anyRequest().authenticated();
//		http.addFilterBefore(authenticationJwtTokenFilter(),UsernamePasswordAuthenticationFilter.class);
//	}
	
	//Defining proper security for accessing resources
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(entryPoint)
	.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	.and().authorizeRequests().antMatchers("/api/auth/**").permitAll()
	.antMatchers(HttpMethod.GET,"/api/comments/id/**").permitAll()
	.antMatchers("/api/blogs/","/api/blogs/id/**").permitAll().antMatchers("/download/{fileName}","/downloadtesting/{fileName}").permitAll()
	.antMatchers("/api/blogs/createblog","/api/blogs/name").fullyAuthenticated()
	.antMatchers("/v2/api-docs",
	"/configuration/ui",
	"/swagger-resources/**",
	"/configuration/security",
	"/swagger-ui.html",
	"/webjars/**")
	.permitAll()
	.anyRequest().authenticated();
	http.addFilterBefore(authenticationJwtTokenFilter(),UsernamePasswordAuthenticationFilter.class);
	}
}
