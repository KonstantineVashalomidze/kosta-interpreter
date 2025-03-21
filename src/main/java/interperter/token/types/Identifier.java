package interperter.token.types;


import interperter.token.Precedence;
import interperter.token.Token;

import static interperter.token.Precedence.LOWEST;


public class Identifier
    implements Token
{
    private String literal;


    @Override
    public Precedence precedence() {
        return LOWEST;
    }

    @Override
    public String literal() {
        return literal;
    }


    public Token setLiteral(String literal) {
        this.literal = literal;
        return this;
    }
}
