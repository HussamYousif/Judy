package Parsing;

import AST.*;
import ErrorHandling.TokenMisMatchException;
import ErrorHandling.TypeException;
import Lexing.Token;
import Lexing.TokenType;
import Util.TokenList;

import java.util.ArrayList;


public class ParseConditionals {

    /**
     * If expr Do [statementList] End
     *
     * This follows the pattern of the entire program really: check for tokens, ensure that the given tokens are correct.
     */
    private static Statement parseIf(TokenList tokens) throws TokenMisMatchException, TypeException {
        Token token = tokens.pop();
        TokenMisMatchException.awaitedToken(TokenType.IF, token); //If

        // Condition
        Expr condition = ParseExpression.parseExpr(tokens, TokenType.DO);

        TokenMisMatchException.awaitedToken(TokenType.DO, tokens.pop()); // Do
        If ifStatement = new If(condition);

        while(tokens.peek().getTokenType() != TokenType.ELSEIF && tokens.peek().getTokenType() != TokenType.ELSE
                && tokens.peek().getTokenType() != TokenType.END) {
            Statement s = ParseStatement.parse(tokens);
            ifStatement.addDoStatement(s);
        }
        if (tokens.peek().getTokenType() == TokenType.END)
            tokens.pop();

        return ifStatement;
    }

    /**
     * Else If expr DO [statementList] End
     */
    private static Statement parseElseIf(TokenList tokens) throws TokenMisMatchException, TypeException {
        Token token = tokens.pop();
        TokenMisMatchException.awaitedToken(TokenType.ELSEIF, token);

        Expr condition = ParseExpression.parseExpr(tokens, TokenType.DO);
        TokenMisMatchException.awaitedToken(TokenType.DO, tokens.pop());

        ElseIf e = new ElseIf(condition);
        while(tokens.peek().getTokenType() != TokenType.END ||
                tokens.peek().getTokenType() != TokenType.ELSE ||
                tokens.peek().getTokenType() != TokenType.ELSEIF ){
            Statement s = ParseStatement.parse(tokens);
            e.addStatement(s);
        }

        return e;
    }

    /**
     * Else Do
     *      [statementList]
     * End
     */
    private static Statement parseElse(TokenList tokens) throws TokenMisMatchException, TypeException {
        Token token = tokens.pop();
        TokenMisMatchException.awaitedToken(TokenType.ELSE, token);
        TokenMisMatchException.awaitedToken(TokenType.DO, tokens.pop());

        Else e = new Else();
        while(tokens.peek().getTokenType() != TokenType.END) {
            Statement s = ParseStatement.parse(tokens);
            e.addstatement(s);
        }
        return e;
    }

    /**
     * If expr Do
     *      StatementList
     *
     * Else If expr Do
     *      StatementList
     *
     * Else Do
     *      StatementList
     * End
     *
     */
    public static Statement parse(TokenList tokens) throws TokenMisMatchException, TypeException {
        // If something DO
        TokenMisMatchException.awaitedToken(TokenType.IF, tokens.peek());

        Statement ifStatement = parseIf(tokens);

        // Remember to pop the token in the case that it is an end token.
        // The next token is neither elsif, else nor an end
        if (tokens.peek().getTokenType() != TokenType.ELSEIF && tokens.peek().getTokenType() != TokenType.ELSE) {
            return ifStatement;
        }


        ArrayList<Statement> elseIfs = null;

        if (tokens.peek().getTokenType() == TokenType.ELSEIF) {
            elseIfs = new ArrayList<Statement>();
            while (tokens.peek().getTokenType() == TokenType.ELSEIF) {
                Statement e = parseElseIf(tokens);
                elseIfs.add(e);
            }
        }

        Statement elseStatement = parseElse(tokens);
        TokenMisMatchException.awaitedToken(TokenType.END, tokens.pop()); // Pop the End token.

       return new Conditionals(ifStatement, elseIfs, elseStatement);
    }


}
