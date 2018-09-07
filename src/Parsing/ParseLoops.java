package Parsing;

import AST.*;
import ErrorHandling.TokenMisMatchException;
import ErrorHandling.TypeException;
import Lexing.TokenType;
import Util.TokenList;


public class ParseLoops {



    public static Statement parse(TokenList tokens) throws TypeException, TokenMisMatchException {

        if (tokens.peek().getTokenType() == TokenType.FOR) {
            return parseFor(tokens);
        }

        if (tokens.peek().getTokenType() == TokenType.WHILE) {
            return parseWhile(tokens);
        }

        return null;
    }

    /*
    *   While expr Do
    *       [StatementList]
    *   End
     */
    public static Statement parseWhile(TokenList tokens) throws TokenMisMatchException, TypeException {
        TokenMisMatchException.awaitedToken(TokenType.WHILE, tokens.pop());

        IAST expr = ParseExpression.parseExpr(tokens, TokenType.DO);

        TokenMisMatchException.awaitedToken(TokenType.DO, tokens.pop());
        While whileStatement = new While(expr);

        while(tokens.peek().getTokenType() != TokenType.END) {
            whileStatement.addStatement(ParseStatement.parse(tokens));
        }

        tokens.pop(); // End

        return whileStatement;

    }

    /*
    * FOR Statement (init) ; Expression (condition) ; Statement (update) ; Do
    *          [StatementList]
    * End
     */
    public static Statement parseFor(TokenList tokens) throws TokenMisMatchException, TypeException {
        TokenMisMatchException.awaitedToken(TokenType.FOR, tokens.pop());

        Statement init = ParseStatement.parse(tokens);
        IAST conditionExpr = ParseExpression.parseExpr(tokens, TokenType.SEMICOLON);
        tokens.pop(); // Removing the semicolon.
        Statement update =  ParseStatement.parse(tokens);

        TokenMisMatchException.awaitedToken(TokenType.DO, tokens.pop());
        For forLoop = new For(init, conditionExpr, update);

        // Parse the body.
        while(tokens.peek().getTokenType() != TokenType.END) {
            forLoop.addStatement(ParseStatement.parse(tokens));

            // TODO Maybe this should be after the loop?
            if (tokens.peek().getTokenType() == TokenType.END) {
                tokens.pop(); // Pops the end.
                break;
            }

            if (tokens.peek().getTokenType() == TokenType.SEMICOLON) {
                tokens.pop(); // Removing the semicolon.
            }

        }

        return forLoop;
    }
}
