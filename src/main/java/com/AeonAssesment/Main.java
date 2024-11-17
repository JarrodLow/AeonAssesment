package com.AeonAssesment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableTransactionManagement
@ComponentScan(basePackages = {
		"com.AeonAssesment"
})
@EnableJpaRepositories(basePackages = {
		"com.AeonAssesment"

})
@EntityScan(basePackages = {
		"com.AeonAssesment"
})
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
