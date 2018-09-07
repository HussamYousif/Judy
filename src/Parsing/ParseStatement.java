package Parsing;

import AST.*;
import ErrorHandling.TokenMisMatchException;
import ErrorHandling.TypeException;
import Lexing.Token;
import Lexing.TokenType;
import Util.TokenList;

import javax.swing.plaf.nimbus.State;
import java.util.ArrayList;


public class ParseStatement {



    // The method that controls the flow of statement parsing.
    public static Statement parse(TokenList tokens) throws TokenMisMatchException, TypeException {

        if (tokens.isFunctionCall()) {
            FunctionCall fCall =  ParseFunctionCall.parse(tokens, ParseIdentifier.parse(tokens));
            fCall.setStmt();
            TokenMisMatchException.awaitedToken(TokenType.SEMICOLON, tokens.pop());
            return fCall;
        }

        if (tokens.peek().getTokenType() == TokenType.PRINT) {
            return ParsePrint.parse(tokens);
        }

        if (tokens.peek().getTokenType() == TokenType.CONTINUE) {
            tokens.pop();  // The continue token.
            TokenMisMatchException.awaitedToken(TokenType.SEMICOLON, tokens.pop());
            return new Continue();
        }

        if (tokens.peek().getTokenType() == TokenType.BREAK) {
            tokens.pop(); // The break token.
            TokenMisMatchException.awaitedToken(TokenType.SEMICOLON, tokens.pop());
            return new Break();
        }

       if (tokens.peek().getTokenType() == TokenType.RETURN) {
           return ParseReturn.parse(tokens);
       }

       // Declare and Assign: Int a = 5;
       // Declare: Int a;
       else if (isType(tokens)) {
            return ParseDeclare.Parse(tokens);
       }

       else if (tokens.peek().getTokenType() == TokenType.IF) {
           return ParseConditionals.parse(tokens);
       }

       else if (tokens.peek().getTokenType() == TokenType.FOR || tokens.peek().getTokenType() == TokenType.WHILE) {
           return ParseLoops.parse(tokens);
       }

       // Assign: a = 5;
       else if (tokens.peek().getTokenType() == TokenType.IDENTIFIER) {
           return ParseAssigment.parse(tokens);
       }

        else {
           throw new TokenMisMatchException("Statement", tokens.pop());
        }
    }

   public static boolean isType(TokenList tokens) {
        Token t = tokens.peek();
        return (t.getTokenType() == TokenType.INT || t.getTokenType() == TokenType.CHAR ||
                t.getTokenType() == TokenType.SHORT || t.getTokenType() == TokenType.LONG
        || t.getTokenType() == TokenType.FLOAT || t.getTokenType() == TokenType.DOUBLE);
   }


}
