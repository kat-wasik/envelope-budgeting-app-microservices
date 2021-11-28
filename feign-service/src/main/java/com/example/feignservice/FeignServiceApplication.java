package com.example.feignservice;

import com.example.feignservice.config.SwaggerConfig;
import com.example.feignservice.model.User;
import com.example.feignservice.repository.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableAsync
@Import(SwaggerConfig.class)
public class FeignServiceApplication {
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(FeignServiceApplication.class, args);
	}

	@Bean
	InitializingBean createUsers() {
		return () -> {
			userRepository.save(User
					.builder()
					.username("kat@abc.com")
					.password("$2a$10$6LVAjeKW2L9KfD17qiTTGuOAJ2OS6hJ6b2WMp6GKzswiy32G/vLmK")
					.budget(Long.valueOf("1"))
					.build()
			);

			userRepository.save(User
					.builder()
					.username("aga@abc.com")
					.password("$2a$10$6LVAjeKW2L9KfD17qiTTGuOAJ2OS6hJ6b2WMp6GKzswiy32G/vLmK")
					.budget(Long.valueOf("2"))
					.build()
			);
		};
	}
}
