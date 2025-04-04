package com.github.konstantinevashalomidze.interpreter.evaluator.value;


import com.github.konstantinevashalomidze.interpreter.ast.expression.Identifier;
import com.github.konstantinevashalomidze.interpreter.ast.statement.BlockStatement;
import com.github.konstantinevashalomidze.interpreter.evaluator.Environment;

import java.util.List;
import java.util.stream.Collectors;

public class Function
        implements Value {
    private final List<Identifier> parameters;
    private final BlockStatement body;

    private final Environment environment;

    public Function(List<Identifier> parameters, BlockStatement body, Environment environment) {
        this.parameters = parameters;
        this.body = body;
        this.environment = environment;
    }

    @Override
    public String inspect() {
        StringBuilder sb = new StringBuilder();
        String params = parameters.stream().map(Identifier::getValue).collect(Collectors.joining(", "));
        sb.append("fn ").append("(").append(params).append(") {\n").append(body.toString()).append("\n}");
        return sb.toString();
    }

    public BlockStatement getBody() {
        return body;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public List<Identifier> getParameters() {
        return parameters;
    }
}
