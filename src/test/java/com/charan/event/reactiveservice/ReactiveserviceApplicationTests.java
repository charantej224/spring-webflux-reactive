package com.charan.event.reactiveservice;

import com.charan.event.reactiveservice.domain.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.WebClient;
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
        Account account = new Account();
        account.setId("123");
        account.setOwner("Charan");
        account.setValue(new Double(1234567890));


        Mono<Account> response = webClient.post().uri("http://localhost:8090/saveMono")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(account), Account.class)
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(Account.class));
        response.subscribe(System.out::println);

    }

}
