package Parsing;

import AST.Expr;
import AST.IAST;
import AST.Identifier;
import ErrorHandling.TokenMisMatchException;
import Lexing.TokenType;
import Util.TokenList;

public class ParseIdentifier {


    public static Expr parse(TokenList tokens) throws TokenMisMatchException {
        TokenMisMatchException.awaitedToken(TokenType.IDENTIFIER, tokens.peek());
        return new Identifier(tokens.pop().getData());
    }
}
