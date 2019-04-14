package com.charan.event.reactiveservice.controller;


import com.charan.event.reactiveservice.Repositories.AccountRepository;
import com.charan.event.reactiveservice.domain.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    private static Logger logger = LoggerFactory.getLogger(AccountController.class);

    // unable to test/doesn't work
    @PostMapping("/save")
    public Mono<Account> save(@RequestBody Account account) throws InterruptedException {
        logger.info(account.toString());
        logger.info(Thread.currentThread().getName());
        Mono<Account> mpno = accountRepository.save(account);
        //account.subscribe(accountRepository::save);
        Thread.sleep(10000);
        logger.info(Thread.currentThread().getName());
        return mpno;
    }

    // Working
    @PostMapping("/saveMono")
    public Mono<Void> save(@RequestBody Mono<Account> account) {
        logger.info(account.toString());
        account.subscribe(accountRepository::save);
        logger.info("request processed");
        return Mono.empty();
    }

    // Working
    @PostMapping("/saveAll")
    public Flux<Account> save(@RequestBody Flux<Account> account) {
        return accountRepository.saveAll(account);
    }

    // unable to test/doesn't work
    @PostMapping(value = "/save/accounts", consumes = "application/stream+json")
    public Mono<Void> saveAsSteam(@RequestBody Flux<Account> account) {
        accountRepository.saveAll(account);
        return Mono.empty();
    }

    // Working
    @GetMapping("/accounts")
    public Flux<Account> getAccounts(){
        return accountRepository.findAll();
    }


}
