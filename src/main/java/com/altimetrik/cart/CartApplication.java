package com.altimetrik.cart;

import com.altimetrik.cart.service.utils.LoadOnStartUpService;
import info.faljse.SDNotify.SDNotify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class CartApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(CartApplication.class);

	@Autowired
	private LoadOnStartUpService startUpService;

	public static void main(String[] args) {
		SpringApplication.run(CartApplication.class, args);
	}

	/**
	 * Invokes the default on load startup.
	 *
	 * @param event ready event.
	 * @throws Exception exception.
	 */
	@EventListener
	public void onApplicationEvent(ApplicationStartedEvent event) throws Exception {
		LOGGER.info("Notifying service manager about start-up completion");
		SDNotify.sendNotify();
		try {
			startUpService.catalogLoadOnStartUp();
		} catch (Exception ex) {
			LOGGER.error("Execution Result migration failed", ex);
		}
	}
}
