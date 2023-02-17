package com.example.comptecqrses.commonApi.commands;

import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class BaseCommande<T> {
    //Pour la faire lier avec @AggregateIdentifier
    //ILs représentent le meme attribut
    @TargetAggregateIdentifier
    @Getter private T id;

    public BaseCommande(T id) {
        this.id = id;
    }

}
