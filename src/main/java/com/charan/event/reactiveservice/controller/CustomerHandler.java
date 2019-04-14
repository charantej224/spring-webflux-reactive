package com.charan.event.reactiveservice.controller;

import com.charan.event.reactiveservice.Repositories.CustomerRepository;
import com.charan.event.reactiveservice.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Component
public class CustomerHandler {

    @Autowired
    CustomerRepository customerRepository;

    public Mono<ServerResponse> getAllCustomers(ServerRequest serverRequest) {
        Flux<Customer> customerFlux = customerRepository.findAll();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(customerFlux, Customer.class);
    }

    public Mono<ServerResponse> getCustomer(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        Mono<Customer> monoCustomer = customerRepository.findById(id);
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        return monoCustomer.flatMap(customer ->
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(fromObject(customer))).switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> saveCustomer(ServerRequest serverRequest) {
        Mono<Customer> monoCustomer = serverRequest.bodyToMono(Customer.class);
        return monoCustomer.flatMap(customer ->
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(customerRepository.save(customer), Customer.class)
        );
    }

    public Mono<ServerResponse> updateCustomer(ServerRequest serverRequest) {
        Mono<Customer> monoCustomer = serverRequest.bodyToMono(Customer.class);
        String id = serverRequest.pathVariable("id");
        Mono<Customer> existing = customerRepository.findById(id);
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();

        return monoCustomer.zipWith(existing, (customer, existingCustomer) ->
                new Customer(existingCustomer.getId(), customer.getName(), customer.getOccupation(), customer.getBankName())
        ).flatMap(customer ->
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(customerRepository.save(customer), Customer.class)
        ).switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> deleteCustomer(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return ServerResponse.ok().body(customerRepository.deleteById(id), Void.class);
    }

}
