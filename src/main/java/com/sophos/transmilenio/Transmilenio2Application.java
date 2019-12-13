package com.sophos.transmilenio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.*;

import javax.annotation.PostConstruct;


@SpringBootApplication
public class Transmilenio2Application {
	
	@PostConstruct
    void started() {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
	
	public static void main(String[] args) {
		SpringApplication.run(Transmilenio2Application.class, args);
	}

}
