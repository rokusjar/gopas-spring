package cz.gopas.eshop;

import org.dozer.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;

@SpringBootApplication
public class EshopWebApplication {

	//chceme aby nas dozer mapper byl Singleton
	@Bean
	public DozerBeanMapper mapper(){
		return new DozerBeanMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(EshopWebApplication.class, args);
	}
}
