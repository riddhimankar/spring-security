package com.saral.accountService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class AccountServiceApplication {

	private static final Logger logger = LoggerFactory.getLogger(AccountServiceApplication.class);

	public static void main(String[] args) throws UnknownHostException {

		var app = new SpringApplication(AccountServiceApplication.class);
		var env = app.run(args).getEnvironment();
		var appName = env.getProperty("spring.application.name");
		var port = env.getProperty("server.port");

		String externalUrl = InetAddress.getLocalHost().getHostAddress();

		logger.info("---------------------------------------------------------");
		logger.info(" Application '{}' is running! Access URLs:", appName);
		logger.info(" Local:     http://localhost:{}", port);
		logger.info(" External:  http://{}:{}",externalUrl ,port);
		logger.info("---------------------------------------------------------");
	}

}
