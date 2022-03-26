package it.prova.gestionepermessi.security;

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
public class SecSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
    private CustomAuthenticationSuccessHandlerImpl successHandler;
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
 
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
         .userDetailsService(customUserDetailsService);
         //.passwordEncoder(passwordEncoder());
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	 http.authorizeRequests()
         .antMatchers("/assets/**").permitAll()
         .antMatchers("/login").permitAll()
         .antMatchers("/utente/**"/*, "utente/insert", "utente/list", "utente/search", "utente/show*/).hasRole("ADMIN")
         .antMatchers("/dipendente", "/dipendente/search", "/dipendente/list", "/dipendente/show/**").hasAnyRole("ADMIN","BO_USER")
         .antMatchers("/dipendente/**").hasRole("BO_USER")
         .antMatchers("/richieste_permesso/search", "/richieste_permesso/cambiaApprovazione"/*"/richieste_permesso/list"*/).hasRole("BO_USER")
         .antMatchers("/richieste_permesso/search_personal","/richieste_permesso/insert", "/richieste_permesso/edit/**").hasRole("DIPENDENTE_USER")
         .antMatchers("/richieste_permesso/list", "/richieste_permesso/show/**").hasAnyRole("BO_USER", "DIPENDENTE_USER")
         .antMatchers("/messaggio/**").hasRole("BO_USER")
         .antMatchers("/**").hasAnyRole("ADMIN", "DIPENDENTE_USER", "BO_USER")
         //.antMatchers("/anonymous*").anonymous()
         .anyRequest().authenticated()
         .and().exceptionHandling().accessDeniedPage("/accessDenied")
         .and()
         	.formLogin()
         	.loginPage("/login")
         	//.defaultSuccessUrl("/home",true)
         	//uso un custom handler perché voglio mettere delle user info in session
         	.successHandler(successHandler)
         	.failureUrl("/login?error=true")
         	.permitAll()
         .and()
         	.logout()
         	.logoutSuccessUrl("/executeLogout")
            .invalidateHttpSession(true)
            .permitAll()
         .and()
            .csrf()
            .disable();
//         
    }
}
