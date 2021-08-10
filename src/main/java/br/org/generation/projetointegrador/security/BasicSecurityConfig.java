package br.org.generation.projetointegrador.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity //habilita segurança na nossa api
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired //injeçao de dependencia (é quando se dá o controle para o Spring de determinada classe)
	private  UserDetailsServiceImpl userDetailsService;

	@Override //annotation para sobreescrever metodo
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		
		auth.inMemoryAuthentication()
		.withUser("root")
		.password(passwordEncoder().encode("root"))
		.authorities("ROLE_USER");
	
		
	}
	
	@Bean //semelhante a uma injeção, mas que pode ser usado em qualquer parte do código, sendo pública.
	public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Override //annotation para sobreescrever metodo
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/**").permitAll()
		.antMatchers("/usuarios/cadastrar").permitAll()
		.antMatchers("/usuarios/logar").permitAll()
		.antMatchers(HttpMethod.GET, "/postagem").permitAll()
		.antMatchers(HttpMethod.GET, "/tema").permitAll()
		.antMatchers(HttpMethod.POST, "/postagem").permitAll()
		.antMatchers(HttpMethod.POST, "/tema").permitAll()
		.antMatchers(HttpMethod.PUT, "/postagem").permitAll()
		.antMatchers(HttpMethod.PUT, "/tema").permitAll()
		.antMatchers(HttpMethod.DELETE, "/postagem").permitAll()
		.antMatchers(HttpMethod.DELETE, "/tema").permitAll()
		.anyRequest().authenticated()
 		.and().httpBasic()
 		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//statelles não guarda sessão
 		.and().cors()
 		.and().csrf().disable(); //desabilita o csrf -> Cross-site request forgery
	}
	
	
}