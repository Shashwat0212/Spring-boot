package com.shopme.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.shopme.security.oauth.CustomerOAuth2UserService;
import com.shopme.security.oauth.OAuth2LoginSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Autowired
	private CustomerOAuth2UserService oauth2UserService;
	@Autowired
	private OAuth2LoginSuccessHandler oauth2LoginSuccessHandler;
	@Autowired
	private DatabaseLoginSuccessHandler databaseLoginHandler;
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
				.requestMatchers("/account_details", "/update_account_details",
						"/cart", "/address_book/**")
				.authenticated().anyRequest().permitAll().and().formLogin()
				.loginPage("/login").usernameParameter("email")
				.successHandler(databaseLoginHandler).permitAll().and()
				.oauth2Login().loginPage("/login").userInfoEndpoint()
				.userService(oauth2UserService).and()
				.successHandler(oauth2LoginSuccessHandler).and().logout()
				.permitAll().and().rememberMe()
				.key("abcdefghijklmnopqrs_1234567890")
				.tokenValiditySeconds(14 * 24 * 60 * 60).and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
		return http.build();
	}
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers("/images/**", "/js/**",
				"/webjars/**");
	}
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomerUserDetailsService();
	}
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}
}
