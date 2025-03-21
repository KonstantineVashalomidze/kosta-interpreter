package interperter.token.types;


import interperter.token.Precedence;
import interperter.token.Token;

import static interperter.token.Precedence.EQUALS;

public class Eq implements Token {


    @Override
    public Precedence precedence() {
        return EQUALS;  // Equality operators have lower precedence than comparisons
    }

    @Override
    public String literal() {
        return "==";
    }

    @Override
    public Token setLiteral(String string) {
        return this;
    }
}
