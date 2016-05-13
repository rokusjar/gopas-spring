package cz.gopas.eshop;

import java.util.*;
import javax.servlet.*;
import org.dozer.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.*;
import org.springframework.cache.annotation.*;
import org.springframework.cache.concurrent.*;
import org.springframework.cache.support.*;
import org.springframework.context.annotation.*;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.*;

@EnableCaching   //abych mohl kesovat
@EnableScheduling      //abych mohl pouzivat scheduled
@SpringBootApplication
@ImportResource("classpath:security.xml") //zapojujeme do kontejneru konfiguraci ze souboru security.xml
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

	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {

		return new EmbeddedServletContainerCustomizer() {
			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {
				ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
				container.addErrorPages(error404Page);
				container.addInitializers(new ServletContextInitializer() {

					@Override
					public void onStartup(ServletContext servletContext) throws ServletException {
						servletContext.setSessionTrackingModes(EnumSet.of(SessionTrackingMode.COOKIE));
					}
				});
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(EshopWebApplication.class, args);
	}
}
