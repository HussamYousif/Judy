package Parsing;

import AST.*;
import ErrorHandling.TokenMisMatchException;
import Lexing.TokenType;
import Util.TokenList;


public class ParsePrint {


    public static Statement parse(TokenList tokens) throws TokenMisMatchException {
        TokenMisMatchException.awaitedToken(TokenType.PRINT, tokens.pop());
        TokenMisMatchException.awaitedToken(TokenType.STRING, tokens.peek());

        // Expects a String!
        Print p = new Print(tokens.pop().getData());

        //Expects an expressions now.
        while(tokens.peek().getTokenType() != TokenType.SEMICOLON) {
            TokenMisMatchException.awaitedToken(TokenType.COMMA, tokens.pop());
            p.addExpr(ParseExpression.parseExpr(tokens, TokenType.SEMICOLON));
        }

        TokenMisMatchException.awaitedToken(TokenType.SEMICOLON, tokens.pop()); // Semicolon for consistency.

        return p;
    }
}
