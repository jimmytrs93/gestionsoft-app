package com.imagine.gestionsoft.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.imagine.gestionsoft.core.GestionsoftCoreConf;

@Import({ GestionsoftCoreConf.class })
@SpringBootApplication
public class GestionsoftApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionsoftApiApplication.class, args);
	}

}
