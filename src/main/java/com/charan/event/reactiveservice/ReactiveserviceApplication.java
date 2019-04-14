package com.charan.event.reactiveservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import javax.annotation.PostConstruct;
import java.util.Optional;

@SpringBootApplication
@EnableReactiveMongoRepositories
@ComponentScan("com.charan.event.reactiveservice.*")
public class ReactiveserviceApplication {

    private final Environment environment;
    private static Logger logger = LoggerFactory.getLogger(ReactiveserviceApplication.class);

    ReactiveserviceApplication(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void init() {
        Optional<String> serverPort = Optional.ofNullable(environment.getProperty("server.port"));
        Optional<String> contextPath = Optional.ofNullable(environment.getProperty("server.servlet.context-path"));
        logger.info("http://localhost:{}/{}", serverPort.orElse(""), contextPath.orElse(""));
    }


    public static void main(String[] args) {
        SpringApplication.run(ReactiveserviceApplication.class, args);
    }

}
