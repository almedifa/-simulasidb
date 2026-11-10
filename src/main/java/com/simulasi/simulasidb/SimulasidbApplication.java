package com.simulasi.simulasidb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SimulasidbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimulasidbApplication.class, args);
	}

}
