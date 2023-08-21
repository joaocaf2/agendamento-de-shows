package com.agendamento.shows.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	@Autowired
	private LoginSucessHandler loginSucessHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/login").permitAll().antMatchers("/produto/formulario/**").hasRole("ADM")
				.antMatchers("/js/**").permitAll().antMatchers("/css/**").permitAll().antMatchers("/carrinho")
				.permitAll().antMatchers("/desenvolvedor").permitAll().antMatchers("/cliente/**").permitAll()
				.antMatchers("/sessaotimeout").permitAll().anyRequest().authenticated().and()
				.formLogin(form -> form.loginPage("/login").successHandler(loginSucessHandler).permitAll()
						.loginProcessingUrl("/login").failureUrl("/login?erro=1"))
				.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/").deleteCookies("JSESSIONID")).csrf()
				.disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(new BCryptPasswordEncoder());
	}

}
