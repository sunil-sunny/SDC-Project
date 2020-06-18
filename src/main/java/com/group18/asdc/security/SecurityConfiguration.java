package com.group18.asdc.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/public/**", "/forgot-password", "/registration", "/home", "/resetPassword","/login").permitAll()
                .anyRequest().authenticated().and().formLogin().loginPage("/login").failureUrl("/login-error")
                .defaultSuccessUrl("/login-success").permitAll().and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID").permitAll();

    }

    @Override
	protected AuthenticationManager authenticationManager() throws Exception
	{
		return new CustomAuthenticationManager();
	}


    /*
		 * http.authorizeRequests().antMatchers("/instructedcourses").hasRole(
		 * "INSTRUCTOR").antMatchers("/enrolledcourses")
		 * .hasRole("STUDENT").antMatchers("/tacourses").hasRole("TA").antMatchers(
		 * "/instructedcourses")
		 * .hasRole("INSTRUCTOR").antMatchers("/coursepage").hasRole("STUDENT")
		 * .antMatchers("/coursepageInstrcutor").hasAnyRole("INSTRUCTOR",
		 * "TA").antMatchers("/allocateTA") .hasAnyRole("INSTRUCTOR",
		 * "TA").antMatchers("/uploadstudents").hasAnyRole("INSTRUCTOR", "TA");
		 */
        
    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // auth.inMemoryAuthentication()
        // .withUser("rob").password("{noop}rob").roles("ADMIN").and()
        // .withUser("student").password("{noop}student").roles("GUEST");
        // .passwordEncoder(passwordEncoder());

    //     auth.jdbcAuthentication().dataSource(SystemConfig.getSingletonInstance().getDataSource()).passwordEncoder(CommonUtil.getInstance().passwordEncoder())
    //             .usersByUsernameQuery(SQLQueries.USER_AUTH_BY_EMAIL.toString())
    //             .authoritiesByUsernameQuery(SQLQueries.GET_USER_ROLES.toString());
    // }

}