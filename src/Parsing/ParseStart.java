package Parsing;

import AST.Start;
import ErrorHandling.TokenMisMatchException;
import ErrorHandling.TypeException;
import Lexing.Token;
import Lexing.TokenType;
import Util.TokenList;

/**
 * Created by hussam on 18.04.17.
 */
public class ParseStart {

    /**
     * Start:
     *  [StatementList]
     *  End
     */
    public static Start parse(TokenList tokens) throws TokenMisMatchException, TypeException {
        TokenMisMatchException.awaitedToken(TokenType.START, tokens.pop()); // Start
        TokenMisMatchException.awaitedToken(TokenType.COLON, tokens.pop()); // :
        Start start = new Start();

        // [StatementList]
        while(tokens.peek().getTokenType() != TokenType.END) {
            start.addStatement(ParseStatement.parse(tokens));
        }

        TokenMisMatchException.awaitedToken(TokenType.END, tokens.pop()); // End

        return start;
    }
}
