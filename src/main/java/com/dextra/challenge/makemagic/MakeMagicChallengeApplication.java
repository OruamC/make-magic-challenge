package com.dextra.challenge.makemagic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MakeMagicChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MakeMagicChallengeApplication.class, args);
	}

}
