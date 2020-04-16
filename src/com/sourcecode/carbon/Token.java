package com.sourcecode.carbon;

public class Token {

    protected final TokenType tokenType;
    protected final String lexeme;
    protected final Object literal;
    protected final int line;

    public Token(TokenType tokenType, String lexeme, Object literal, int line) {
        this.tokenType = tokenType;
        this.lexeme = lexeme;
        this.literal = literal;
        this.line = line;
    }

    public String toString() {
        return tokenType + " " + lexeme + " " + literal;
    }
}
