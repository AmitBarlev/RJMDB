package com.abl;

import lombok.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;

@Generated
@SpringBootApplication(exclude = {R2dbcAutoConfiguration.class})
public class RjmdbApplication {

	public static void main(String[] args) {
		SpringApplication.run(RjmdbApplication.class, args);
	}

}
