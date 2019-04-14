package com.charan.event.reactiveservice.Repositories;

import com.charan.event.reactiveservice.domain.Account;
import com.charan.event.reactiveservice.domain.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, String> {

    Flux<Account> findAllByName(String value);
    Mono<Account> findFirstByOccupation(String owner);

}
