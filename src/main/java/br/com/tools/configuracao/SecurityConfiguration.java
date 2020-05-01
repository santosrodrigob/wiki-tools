package br.com.tools.configuracao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
				
		auth.jdbcAuthentication().dataSource(dataSource)
        .usersByUsernameQuery(""
        		+ "select "
        		+ "	codigo_usuario, "
        		+ "	senha, "
        		+ "	ativo "
            + " from "
            + "	tb_usuario where codigo_usuario = ?")
        .authoritiesByUsernameQuery(""
        		+ "select "
        		+ "	r.codigo_acesso, "
        		+ "	r.acesso "
            + "from "
            + "	tb_usuario a "
            + "	inner join tb_role r on a.codigo_acesso_wiki=r.codigo_acesso "
            + "where "
            + "	codigo_usuario = ?");
//        .passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		http.addFilterBefore(filter, CsrfFilter.class);
		
		http.authorizeRequests()
//		.antMatchers(HttpMethod.POST, "/referencia/open").hasRole("ADMIN") //bloqueio por method
//		.antMatchers(HttpMethod.POST, "/referencia/open").permitAll() //liberação por method
//		.antMatchers("/login/**").permitAll() //libera tudo a frente
//		.antMatchers(HttpMethod.GET, "/referencia/lista-referencia").hasRole("ADMIN")
		.antMatchers("/referencia/open/0").hasRole("ADMIN")
		.antMatchers("/sub-referencia/open/0").hasRole("ADMIN")
		.antMatchers("/licao/open/0").hasRole("ADMIN")
		.antMatchers("/usuario/open").hasRole("ADMIN")
		.antMatchers("/licao/form-editar").hasRole("ADMIN")
		.antMatchers("/usuario/form-editar").hasRole("ADMIN")
		.antMatchers("/referencia/form-editar-referencia").hasRole("ADMIN")
		.antMatchers("/sub-referencia/form-editar-sub").hasRole("ADMIN")
//		.antMatchers(HttpMethod.GET, "/referencia/lista-referencia").permitAll()
//		.antMatchers(HttpMethod.GET, "/referencia/lista-referencia").permitAll()
//		.antMatchers(HttpMethod.GET, "/sub-referencia/lista-sub").permitAll()
//		.antMatchers(HttpMethod.GET, "/licao/lista").permitAll()
//		.antMatchers("/referencia/open/1").permitAll()
//		.antMatchers("/sub-referencia/open/1").permitAll()
//		.antMatchers("/licao/open/1").permitAll()
//		.antMatchers("/ajaxComboSubReferencia").permitAll()
//		.antMatchers("/assets/**").permitAll()
		.antMatchers("/**").permitAll()
		.antMatchers("/").permitAll()
		.anyRequest().authenticated()
		.and().formLogin().loginPage("/login").permitAll()
		.defaultSuccessUrl("/licao/open/1")
		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
		.and().csrf().disable();
	}
}
