package Parsing;

import AST.Declare;
import AST.IAST;
import AST.Identifier;
import AST.MetaData.GetTypeFromToken;
import AST.MetaData.MyDataType;
import AST.Statement;
import ErrorHandling.TokenMisMatchException;
import ErrorHandling.TypeException;
import Lexing.TokenType;
import Util.TokenList;

import static Lexing.TokenType.SEMICOLON;

/**
 * Parsing declare and declare and assign.
 */
public class ParseDeclare {

    public static Statement Parse(TokenList tokens) throws TokenMisMatchException, TypeException {

        MyDataType type = GetTypeFromToken.getType(tokens);
        TokenMisMatchException.awaitedToken(TokenType.IDENTIFIER, tokens.peek());

        IAST id = ParseIdentifier.parse(tokens);

        // int a;
        if (tokens.peek().getTokenType() == SEMICOLON) {
            tokens.pop();
            return new Declare(type, id);
        }

        // int a = expr;
        TokenMisMatchException.awaitedToken(TokenType.EQ, tokens.pop());

        IAST expr = ParseExpression.parseExpr(tokens, SEMICOLON);
        tokens.pop(); // Semicolon.
        return new Declare(type, id, expr);
    }

}
