package com.charan.event.reactiveservice.Repositories;

import com.charan.event.reactiveservice.domain.Account;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface AccountRepository extends ReactiveCrudRepository<Account, String> {

    Flux<Account> findAllByValue(String value);
    Mono<Account> findFirstByOwner(String owner);

}
