package com.example.comptecqrses.commands.aggregates;

import com.example.comptecqrses.commonApi.commands.CreateAccountCommand;
import com.example.comptecqrses.commonApi.commands.CreditAccountCommand;
import com.example.comptecqrses.commonApi.commands.DebitAccountCommand;
import com.example.comptecqrses.commonApi.enums.AccountStatus;
import com.example.comptecqrses.commonApi.events.AccountActivatedEvent;
import com.example.comptecqrses.commonApi.events.AccountCreatedEvent;
import com.example.comptecqrses.commonApi.events.AccountCreditedEvent;
import com.example.comptecqrses.commonApi.events.AccountDebitedEvent;
import com.example.comptecqrses.commonApi.exceptions.AmounNegativeException;
import com.example.comptecqrses.commonApi.exceptions.BalanceNotSufficentException;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class AccountAggregate {
    @AggregateIdentifier
    private String accountId;
    private double balance;
    private String currency;
    private AccountStatus status;

    public AccountAggregate() {
        //Required By AXON
    }
    @CommandHandler
    //fonction de décision
    public AccountAggregate(CreateAccountCommand createAccountCommand) {
        //Logique métier
        if(createAccountCommand.getInitialBalance()<0) throw new RuntimeException("Impossible...");
        AggregateLifecycle.apply(new AccountCreatedEvent(
                createAccountCommand.getId(),
                createAccountCommand.getInitialBalance(),
                createAccountCommand.getCurrency(),
                AccountStatus.CREATED));
    }

    //Fonction d'évolution pas de logic metier
    @EventSourcingHandler
    public void on(AccountCreatedEvent event){
        this.accountId=event.getId();
        this.balance=event.getInitialBalance();
        this.currency=event.getCurrency();
        this.status=AccountStatus.CREATED;
        AggregateLifecycle.apply(new AccountActivatedEvent(
                event.getId(),
                AccountStatus.ACTIVATED
        ));

    }
    @EventSourcingHandler
    public void on(AccountActivatedEvent event){
        this.status=event.getStatus();
    }
    @CommandHandler
    public void handle(CreditAccountCommand command){
        if(command.getAmount()<0) throw new AmounNegativeException("Amount should not be négative");
        AggregateLifecycle.apply(new AccountCreditedEvent(
                command.getId(),
                command.getAmount(),
                command.getCurrency()
        ));
    }
    //mettre à jour l'état de l'application
    @EventSourcingHandler
    public void on(AccountCreditedEvent event){
        this.balance+=event.getAmount();
    }

    @CommandHandler
    public void handle(DebitAccountCommand command){
        if(command.getAmount()<0) throw new AmounNegativeException("Amount should not be negative");
        if(this.balance<command.getAmount()) throw new BalanceNotSufficentException("Balance not sufficient=>"+balance);
        AggregateLifecycle.apply(new AccountDebitedEvent(
                command.getId(),
                command.getAmount(),
                command.getCurrency()
        ));
    }
    //mettre à jour l'état de l'application
    @EventSourcingHandler
    public void on(AccountDebitedEvent event){
        this.balance-=event.getAmount();
    }
}
