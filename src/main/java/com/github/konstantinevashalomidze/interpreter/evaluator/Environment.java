package com.github.konstantinevashalomidze.interpreter.evaluator;


import com.github.konstantinevashalomidze.interpreter.evaluator.value.Value;

import java.util.HashMap;
import java.util.Map;

public class Environment {

    private final Map<String, Value> store;

    private Environment outer; // reference to outer variables

    public Environment() {
        store = new HashMap<>();
    }

    public Environment(Environment environment) {
        store = new HashMap<>();
        this.outer = environment;
    }

    public Value getValue(String name) {
        Value value = store.get(name);
        if (outer != null && value == null)
            value = outer.getValue(name);
        return value;
    }

    public Value putValue(String name, Value value) {
        store.put(name, value);
        return value;
    }


}
