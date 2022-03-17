package com.challenge.domain.rules;

import lombok.AllArgsConstructor;

import java.util.function.Supplier;

@AllArgsConstructor
public class Rule<T> {
    public Supplier<Boolean> condition = null;
    public Supplier<T> process = null;
}
