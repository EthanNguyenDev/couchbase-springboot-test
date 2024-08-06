package com.example.demo;

import com.couchbase.client.java.env.ClusterEnvironment;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.util.logging.Logger;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	Logger logger = Logger.getLogger(DemoApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Creating ClusterEnvironment");
		ClusterEnvironment clusterEnvironment = ClusterEnvironment
			.builder()
			.timeoutConfig(e -> {
				e.connectTimeout(Duration.ofSeconds(10));
				e.disconnectTimeout(Duration.ofSeconds(5));
				e.kvTimeout(Duration.ofSeconds(5));
				e.queryTimeout(Duration.ofSeconds(30));
			})
			.ioConfig(e -> {
				e.enableDnsSrv(false);
				e.tcpKeepAliveTime(Duration.ofSeconds(4));
			})
			.build();
		logger.info("ClusterEnvironment created");
	}
}
