package com.ebsco.eis.medical;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.ebsco.common.logging", "com.ebsco.eis.beanbag",
		"com.ebsco.eis.medical", "com.ebsco.eis.dynamichealth.elasticsearch.beans" })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}