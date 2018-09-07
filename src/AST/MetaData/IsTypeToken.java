package AST.MetaData;


import Lexing.Token;
import Lexing.TokenType;

public class IsTypeToken {

    public static boolean isType(Token token) {
        return (token.getTokenType() == TokenType.INT || token.getTokenType() == TokenType.CHAR || token.getTokenType()
        == TokenType.SHORT || token.getTokenType() == TokenType.LONG || token.getTokenType() == TokenType.FLOAT ||
        token.getTokenType() == TokenType.DOUBLE);
    }
}
