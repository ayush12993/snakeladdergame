package com.ayushm.gametry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableConfigurationProperties
@EntityScan("com.ayushm.gametry")
@ComponentScan("com.ayushm.gametry")
public class GametryApplication {

	public static void main(String[] args) {
		SpringApplication.run(GametryApplication.class, args);
	}
/*
first make the three roles desired in user,admin,moderator
* For signup data - http://localhost:8080/api/auth/signup
{
  "username": "string",
  "email": "string",
  "role": [
    "ROLE_USER"
  ],
  "password": "string"
}

for signin data - http://localhost:8080/api/auth/signin

{
  "username": "rishabh",
  "password": "123456"
}
* */
}
