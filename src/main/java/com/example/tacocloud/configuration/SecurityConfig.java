package com.example.tacocloud.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final DataSource dataSource;

  public SecurityConfig(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    auth.ldapAuthentication()
            .userSearchBase("ou=people")
            .userSearchFilter("(uid={0})")
            .groupSearchBase("ou=groups")
            .groupSearchFilter("member={0}")
            .passwordCompare()
            .passwordEncoder(new BCryptPasswordEncoder())
            .passwordAttribute("passcode")
            .and()
            .contextSource()
            .url("ldap://tacocloud.com:389/dc=tacocloud,dc=com");
  }
}
