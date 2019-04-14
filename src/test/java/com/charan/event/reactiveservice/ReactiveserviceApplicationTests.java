package com.charan.event.reactiveservice;

import com.charan.event.reactiveservice.domain.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        ReactiveserviceApplication.class}
)
public class ReactiveserviceApplicationTests {

    private WebClient webClient;

    @LocalServerPort
    private int port;

    @Before
    public void setup() {
        this.webClient = WebClient.create();
    }


    @Test
    public void contextLoads() {

       Flux<Customer> customerFlux = webClient.get().uri("http://localhost:7609/customers")
                .header("content-type","application/json")
                .exchange().flatMapMany(clientResponse ->
                clientResponse.bodyToFlux(Customer.class));
        System.out.println("printing response");
        customerFlux.subscribe( customer ->
                System.out.println(customer));
    }

}
