package ErrorHandling;

import Lexing.Token;
import Lexing.TokenType;

/**
 * Created by hussam on 15.03.17.
 */
public class TokenMisMatchException extends Exception {


    public TokenMisMatchException(String expected, Token token) throws TokenMisMatchException {
        super(String.format("Token MisMatchException at (line: %d, position: %d) expected %s, actual %s",
                token.getY(), token.getX(), expected, token.getTokenType().toString()));
    }

    public TokenMisMatchException(String message) {
        super(message);
    }

    public static void isCorrectToken(Token expected, Token actual) throws TokenMisMatchException {
        if (expected.getTokenType() != actual.getTokenType()) {
            new TokenMisMatchException(expected.toString(), actual);
        }
    }

    /**
     *
     * @param expected tokentype of the next token
     * @param actual token that is given.
     * @throws TokenMisMatchException
     */
    public static void awaitedToken(TokenType expected, Token actual) throws TokenMisMatchException {
        if (expected != actual.getTokenType()) {
            new TokenMisMatchException(expected.toString(), actual);
        }
    }

    // TODO Add other datatypes
    public static void awaitType(Token actual) throws TokenMisMatchException {
        if (actual.getTokenType() != TokenType.INT)
            new TokenMisMatchException("Datatype", actual);

    }


}
