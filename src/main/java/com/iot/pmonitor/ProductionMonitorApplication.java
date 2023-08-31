package com.iot.pmonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProductionMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductionMonitorApplication.class, args);
	}

}
