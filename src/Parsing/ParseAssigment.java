package Parsing;

import AST.*;
import AST.MetaData.MyDataType;
import ErrorHandling.TokenMisMatchException;
import Lexing.TokenType;
import Util.TokenList;

/**
 * Created by hussam on 19.03.17.
 */
public class ParseAssigment {

    public static Assigment parse(TokenList tokens) throws TokenMisMatchException {
        IAST val = ParseIdentifier.parse(tokens);
        TokenMisMatchException.awaitedToken(TokenType.EQ, tokens.pop());
        Expr value = ParseExpression.parseExpr(tokens, TokenType.SEMICOLON);
        TokenMisMatchException.awaitedToken(TokenType.SEMICOLON, tokens.pop());


        return new Assigment(StatementType.ASSIGN, val, value);
    }

}
