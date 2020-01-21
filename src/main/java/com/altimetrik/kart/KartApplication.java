package com.altimetrik.kart;

import com.altimetrik.kart.service.OnStartUpService;
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
public class KartApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(KartApplication.class);

	@Autowired
	private OnStartUpService startUpService;

	public static void main(String[] args) {
		SpringApplication.run(KartApplication.class, args);
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
			startUpService.onStartUpLoad();
		} catch (Exception ex) {
			LOGGER.error("Execution Result migration failed", ex);
		}
	}
}
