package Parsing;

import AST.*;
import ErrorHandling.TokenMisMatchException;
import Lexing.Token;
import Lexing.TokenType;
import Util.TokenList;

/**
 * Parses statements of the structure "'return' '=' (expr)';'".
 */
public class ParseReturn {


    public static Statement parse(TokenList tokens) throws TokenMisMatchException {
        TokenMisMatchException.awaitedToken(TokenType.RETURN, tokens.pop());

        IAST n = ParseExpression.parseExpr(tokens, TokenType.SEMICOLON);
        Statement result = new ReturnStatement(StatementType.RETURN, n);


        TokenMisMatchException.awaitedToken(TokenType.SEMICOLON, tokens.pop());

        return result;
    }
}
