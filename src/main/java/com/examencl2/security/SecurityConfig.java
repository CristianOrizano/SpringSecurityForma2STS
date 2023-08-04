package com.examencl2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Autowired
	UserDetailsService userDetailsService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authenticationManager(HttpSecurity httpSecurity, PasswordEncoder passwordEncoder)
			throws Exception {
		return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder).and().build();
	}

	/*
	 * @Autowired public void configurerGlobal(AuthenticationManagerBuilder build)
	 * throws Exception {
	 * build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder(
	 * )); } spring.main.allow-circular-references=true
	 */

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				.csrf(config -> config.disable())
				.authorizeRequests(auth -> {
			auth.antMatchers("/resources/imagen/**", "/css/**", "/js/**").permitAll()// recursos estaticos
					.antMatchers("/inicio").permitAll()
					.antMatchers("/electro/lis", "/electro/grabar" ,"/electro/buscar", "/electro/eliminar","/usuario/**")
					.hasAuthority("ADMIN")
					.antMatchers("/electro/catalogo", "/electro/selecDeta", "/electro/grabarventa")
					.hasAnyAuthority("USER", "ADMIN");
					//.anyRequest().authenticated();
		})	
				 //.httpBasic() // Habilita la autenticación básica
				 //.and()
				.formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/inicio")

				.and().exceptionHandling().accessDeniedPage("/errores/403")
				.and().logout().invalidateHttpSession(true)
				.clearAuthentication(true).logoutSuccessUrl("/login?logout").permitAll()
				.and().build();
	}

	  /*  //usuarios en memoria
    @Bean
      UserDetailsService userDetailsService(BCryptPasswordEncoder encoder){
    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    manager.createUser(User.withUsername("cristian").password(encoder.encode("123")).roles("USER").build());
    manager.createUser(User.withUsername("carlos").password(encoder.encode("456")).roles("ADMIN").build());
    
    return manager;
     } */
    

}
