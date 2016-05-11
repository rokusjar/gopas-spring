package cz.gopas.eshop;

import java.util.*;
import org.dozer.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.*;
import org.springframework.cache.concurrent.*;
import org.springframework.cache.support.*;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.*;

@EnableCaching   //abych mohl kesovat
@EnableScheduling      //abych mohl pouzivat scheduled
@SpringBootApplication
public class EshopWebApplication {

	//chceme aby nas dozer mapper byl Singleton
	@Bean
	public DozerBeanMapper mapper(){
		return new DozerBeanMapper();
	}

	// konfigurace kesovani
	@Bean
	public SimpleCacheManager cacheManager(){
		SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
		simpleCacheManager.setCaches(Arrays.asList(
		new ConcurrentMapCache("items"),
		new ConcurrentMapCache("userOrders")
		//atd - dalsi kese
		));
		return simpleCacheManager;
	}

	public static void main(String[] args) {
		SpringApplication.run(EshopWebApplication.class, args);
	}
}
