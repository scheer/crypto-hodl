package net.muchoviento.cryptohodl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableScheduling;

/** pom.xml made with https://start.spring.io/ */
@SpringBootApplication
@EnableScheduling
public class CryptohodlApplication {

	private static final Logger LOG = LoggerFactory.getLogger(CryptohodlApplication.class);

	@Bean
	@Description("Spring Message Resolver")
	public ResourceBundleMessageSource messageSource() {
    	ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    	messageSource.setBasename("messages");
    	return messageSource;
	}

	public static void main(String[] args) {

		LOG.info("Starting Spring Boot application...");
		SpringApplication.run(CryptohodlApplication.class, args);
		LOG.info("...started SpringApplication.");
	}

}
