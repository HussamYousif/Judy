package Parsing;

import AST.Imports;
import ErrorHandling.TokenMisMatchException;
import Lexing.TokenType;
import Util.TokenList;


public class ParseImports {

    public static Imports parse(TokenList tokens) throws TokenMisMatchException {
        TokenMisMatchException.awaitedToken(TokenType.IMPORTS, tokens.pop());
        Imports i = new Imports(tokens.pop().getData());

        return i;
    }
}
