package com.example.comptecqrses.commonApi.events;

import lombok.Getter;

import javax.validation.constraints.NotNull;

public abstract class BaseEvent<T> {
    @Getter
    private T id;

    public BaseEvent(T id) {
        this.id = id;
    }
}
