package Parsing;

import AST.*;
import ErrorHandling.TokenMisMatchException;
import ErrorHandling.TypeException;
import Lexing.Token;
import Lexing.TokenType;
import Util.TokenList;

import java.util.ArrayList;

/**
 * Created by hussam on 22.03.2017.
 */
public class ParseEvent {

    /**
     * Syntax:
     *
     * Event (Expr); (identifier) :
     *      [StatementList]
     * End
     *
     * Example:
     *
     * Event time > 15:00; watchFuturama:
     *      Tv = futurama;
     * Event
     *
     */
    public static Event parse(TokenList tokens) throws TokenMisMatchException, TypeException {
        TokenMisMatchException.awaitedToken(TokenType.TRIGGER, tokens.pop());
        Expr condition = ParseExpression.parseExpr(tokens, TokenType.SEMICOLON);

        TokenMisMatchException.awaitedToken(TokenType.SEMICOLON, tokens.pop());
        IAST id = ParseIdentifier.parse(tokens);

        // Colon after the identifier!
        TokenMisMatchException.awaitedToken(TokenType.COLON, tokens.pop());
        Event e = new Event(id, condition);

        // Body.
        while(tokens.peek().getTokenType() != TokenType.END) {
            e.addStatement(ParseStatement.parse(tokens));
        }

        TokenMisMatchException.awaitedToken(TokenType.END, tokens.pop());
        return e;
    }
}
