package com.lautypetelin.LoginWithSpringBoot;

import com.lautypetelin.LoginWithSpringBoot.view.DashboardForm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		System.out.println("Spring Boot iniciado.");

		ConfigurableApplicationContext contextoSpring =
				new SpringApplicationBuilder(Main.class)
						.headless(false)
						.web(WebApplicationType.NONE)
						.run(args);

		//Ejecutamos el dashboard
		java.awt.EventQueue.invokeLater(()-> {
			DashboardForm registrationForm = contextoSpring.getBean(DashboardForm.class);
			registrationForm.setVisible(true);
		});
	}
}