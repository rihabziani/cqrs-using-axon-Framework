package com.example.comptecqrses.query.service;

import com.example.comptecqrses.commonApi.enums.OperationType;
import com.example.comptecqrses.commonApi.events.AccountActivatedEvent;
import com.example.comptecqrses.commonApi.events.AccountCreatedEvent;
import com.example.comptecqrses.commonApi.events.AccountCreditedEvent;
import com.example.comptecqrses.commonApi.events.AccountDebitedEvent;
import com.example.comptecqrses.commonApi.queries.GetAllAccountsQuery;
import com.example.comptecqrses.query.entities.Account;
import com.example.comptecqrses.query.entities.Operation;
import com.example.comptecqrses.query.repositories.AccountRepository;
import com.example.comptecqrses.query.repositories.OperationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional //pour faire la mise à jour
public class AccountServiceHandler {
    private AccountRepository accountRepository;
    private OperationRepository operationRepository;
    @EventHandler
    public void on(AccountCreatedEvent event){
        log.info("*****************");
        log.info("AccountCreatedEvent received");
        Account account=new Account();
        account.setId(event.getId());
        account.setAmount(event.getInitialBalance());
        account.setCurrency(event.getCurrency());
        account.setStatus(event.getStatus());
        account.setOperations(new ArrayList<>());
        accountRepository.save(account);
    }

    @EventHandler
    public void on(AccountActivatedEvent event){
        log.info("*****************");
        log.info("AccountActivatedEvent received");
        Account account= accountRepository.findById(event.getId()).get();
        account.setStatus(event.getStatus());
        accountRepository.save(account);
    }

    @EventHandler
    public void on(AccountDebitedEvent event){
        log.info("*****************");
        log.info("AccountDebitedEvent received");
        Account account= accountRepository.findById(event.getId()).get();
        Operation operation=new Operation();
        operation.setAmount(event.getAmount());
        operation.setDate(new Date());
        //il vaut mieux ajouter un attribut date dans event
        //que de prendre la date de création de l'opération sinon on peux avoir du décalage
        operation.setType(OperationType.DEBIT);
        operation.setAccount(account);
        operationRepository.save(operation);
        account.setAmount(account.getAmount()-event.getAmount());
        accountRepository.save(account);
    }

    @EventHandler
    public void on(AccountCreditedEvent event){
        log.info("*****************");
        log.info("AccountCreditedEvent received");
        Account account= accountRepository.findById(event.getId()).get();
        Operation operation=new Operation();
        operation.setAmount(event.getAmount());
        operation.setDate(new Date());
        //il vaut mieux ajouter un attribut date dans event
        //que de prendre la date de création de l'opération sinon on peux avoir du décalage
        operation.setType(OperationType.CREDIT);
        operation.setAccount(account);
        operationRepository.save(operation);
        account.setAmount(account.getAmount()+event.getAmount());
        accountRepository.save(account);
    }
    @QueryHandler
    public List<Account> on(GetAllAccountsQuery query){
        return accountRepository.findAll();
    }

    @QueryHandler
    public Account on(GetAllAccountsQuery query){
        return accountRepository.findAll();
    }
}
