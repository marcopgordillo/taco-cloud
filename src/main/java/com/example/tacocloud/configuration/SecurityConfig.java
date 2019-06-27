package com.example.tacocloud.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final DataSource dataSource;

  public SecurityConfig(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    Map<String, PasswordEncoder> encoders = new HashMap<>();
    encoders.put("bcrypt", new BCryptPasswordEncoder());
    encoders.put("scrypt", new SCryptPasswordEncoder());

    auth.jdbcAuthentication()
            .dataSource(dataSource)
            .usersByUsernameQuery(
                    "select username, password, enabled from Users where username=?")
            .authoritiesByUsernameQuery(
                    "select username, authority from UserAuthorities where username=?")
            .passwordEncoder(new DelegatingPasswordEncoder("bcrypt", encoders));
  }
}
