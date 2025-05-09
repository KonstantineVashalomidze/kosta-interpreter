package com.github.konstantinevashalomidze.interpreter.ast.node;


import com.github.konstantinevashalomidze.interpreter.ast.statement.Statement;

import java.util.ArrayList;
import java.util.List;

/**
 * Program is the root node in AST, it consists of statements represented as array list.
 *
 * @author Konstantine Vashalomdize
 */
public class Program
        implements Node {
    private final List<Statement> statements;

    public Program() {
        statements = new ArrayList<>();
    }

    @Override
    public String literal() {
        if (!statements.isEmpty())
            return statements.getFirst().literal();
        else return "";
    }

    public List<Statement> getStatements() {
        return statements;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Program\n"); // root node
        for (Statement statement : statements) {
            // indent each statement to represent it as a child of the Program node
            sb.append("  |- ").append(statement.toString().replace("\n", "\n  |  ")).append("\n");
        }
        return sb.toString();
    }


}
