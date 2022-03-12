package com.vreal;

import com.vreal.commom.VrealApiProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(VrealApiProperty.class)
public class ValorRealApplication {


	public static void main(String[] args) {

		SpringApplication.run(ValorRealApplication.class, args);
	}

}
