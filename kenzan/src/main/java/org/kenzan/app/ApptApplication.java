package org.kenzan.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import com.kenzan.svc.InitializeData;

@SpringBootApplication
@ComponentScan(basePackages = { "com.kenzan.config","com.kenzan.controller", "com.kenzan.repository", "com.kenzan.svc",
		"com.kenzan.entities" })
@PropertySource("classpath:application.properties")
public class ApptApplication implements ApplicationRunner{
	
	public static void main(String[] args) {
		SpringApplication.run(ApptApplication.class, args);
	}

	@Autowired
	InitializeData ini;
	
	@Override
    public void run(ApplicationArguments args) throws Exception {
		System.out.println("loading data");
		ini.loadDataAfterStartup();
		System.out.println("end loading data");
	}
	
}
