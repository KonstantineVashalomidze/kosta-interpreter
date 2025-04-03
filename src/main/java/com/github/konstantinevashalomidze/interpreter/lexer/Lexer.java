package com.github.konstantinevashalomidze.interpreter.lexer;


import com.github.konstantinevashalomidze.interpreter.token.Token;
import com.github.konstantinevashalomidze.interpreter.token.TokenRegistry;
import com.github.konstantinevashalomidze.interpreter.token.types.*;
import com.github.konstantinevashalomidze.interpreter.token.types.Integer;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.function.Function;

/**
 * Tokenizes the program into tokens. Get that tokens with readAndMoveOnNextToken().
 *
 * @author Konstantine Vashalomidze
 */
public class Lexer {
    private final String input;
    private char currentCharacter;
    private int nextCharacterIndex;
    private Token currentToken;

    private Token previousToken;


    public Lexer(String input) {
        this.input = input;
        moveOnNextCharacter();
    }

    /**
     * Advances currentCharacter to next character and related indexes as well.
     */
    private void moveOnNextCharacter() {
        if (nextCharacterIndex >= input.length())
            currentCharacter = '\0';
        else
            currentCharacter = input.charAt(nextCharacterIndex);
        nextCharacterIndex++;
    }

    /**
     * Directly read next character skipping all white spaces along the way.
     */
    private void skipWhiteSpace() {
        while (currentCharacter == ' ' || currentCharacter == '\t' || currentCharacter == '\n' || currentCharacter == '\r')
            moveOnNextCharacter();
    }

    /**
     * Check what is the next character in the input.
     *
     * @return next character after current character in the input.
     */
    private char nextCharacter() {
        if (nextCharacterIndex >= input.length())
            return '\0';
        return input.charAt(nextCharacterIndex);
    }

    /**
     * reads consecutive characters satisfying provided function
     */
    private String readCurrentLiteral(Function<Character, Boolean> function) {
        StringBuilder literal = new StringBuilder();
        while (function.apply(currentCharacter)) {
            literal.append(currentCharacter);
            moveOnNextCharacter();
        }
        return literal.toString();
    }


    /**
     * sets currentToken by reading set of characters together.
     */
    public Token readAndMoveOnNextToken()
            throws NoSuchMethodException,
            InvocationTargetException,
            InstantiationException,
            IllegalAccessException {
        previousToken = currentToken;
        skipWhiteSpace(); // move to the next currentToken's first character index
        switch (currentCharacter) {
            case '=' -> {
                if (nextCharacter() == '=') {
                    currentToken = Eq.INSTANCE;
                    moveOnNextCharacter();
                } else
                    currentToken = Assign.INSTANCE;
            }
            case '*' -> currentToken = Asterisk.INSTANCE;
            case '!' -> {
                if (nextCharacter() == '=') {
                    currentToken = NotEq.INSTANCE;
                    moveOnNextCharacter();
                } else
                    currentToken = Bang.INSTANCE;
            }
            case ',' -> currentToken = Comma.INSTANCE;
            case '\0' -> currentToken = Eof.INSTANCE;
            case '>' -> currentToken = Gt.INSTANCE;
            case ')' -> currentToken = Rp.INSTANCE;
            case '}' -> currentToken = Rb.INSTANCE;
            case '<' -> currentToken = Lt.INSTANCE;
            case '-' -> currentToken = isPrefix() ? MinusPrefix.INSTANCE : MinusInfix.INSTANCE;
            case '+' -> currentToken = Plus.INSTANCE;
            case '{' -> currentToken = Lb.INSTANCE;
            case '(' -> currentToken = Lp.INSTANCE;
            case ';' -> currentToken = Semicolon.INSTANCE;
            case '/' -> currentToken = Slash.INSTANCE;
            case '&' -> currentToken = And.INSTANCE;
            case '|' -> currentToken = Or.INSTANCE;
            default -> {
                if (Character.isLetter(currentCharacter)) {
                    // Moves to the next currentToken starting index, so we directly return
                    String literal = readCurrentLiteral(Character::isLetter);
                    // Not found in manager means token is identifier.
                    if (TokenRegistry.INSTANCE.getToken(literal).isEmpty()) {
                        currentToken = new Identifier(literal);
                        return currentToken;
                    }
                    // This creates new currentToken depending on the literal
                    // for example if literal is 'fn' then new currentToken of Function will be created.
                    currentToken = TokenRegistry.INSTANCE.getToken(literal).get();
                    return currentToken;
                } else if (Character.isDigit(currentCharacter)) {
                    // this moves to the next currentToken starting index, so we directly return
                    String literal = readCurrentLiteral(Character::isDigit);
                    currentToken = new Integer(literal);
                    return currentToken;
                }
                currentToken = Illegal.INSTANCE;
            }
        }
        moveOnNextCharacter();

        return currentToken;
    }

    private boolean isPrefix() {
        // If there's no previous token (beginning of input or expression),
        // then it's definitely a prefix
        if (previousToken == null)
            return true;

        // Check if the previous token is an operator, opening bracket, or similar token
        // that would indicate this minus is a prefix
        return previousToken instanceof Assign ||
                previousToken instanceof Eq ||
                previousToken instanceof NotEq ||
                previousToken instanceof Bang ||
                previousToken instanceof Comma ||
                previousToken instanceof Rp || // Opening parenthesis
                previousToken instanceof Rb || // Opening brace
                previousToken instanceof Lt ||
                previousToken instanceof Gt ||
                previousToken instanceof Plus ||
                previousToken instanceof MinusInfix ||
                previousToken instanceof MinusPrefix ||
                previousToken instanceof Asterisk ||
                previousToken instanceof Slash ||
                previousToken instanceof And ||
                previousToken instanceof Or ||
                previousToken instanceof Semicolon;

        // Note: The non-prefix case would be after identifiers, integers,
        // or closing brackets/parentheses
    }


}
