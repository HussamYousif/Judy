package ErrorHandling;

import Lexing.Token;


public class IncorrectSyntaxException extends Exception {

    public IncorrectSyntaxException(Token token) throws TokenMisMatchException {
        super(String.format("Token MisMatchException at (line: %d, position: %d) actual %s",
                token.getY(), token.getX(), token.getTokenType().toString()));
    }

    public static void throwSyntaxException(Token actual) throws TokenMisMatchException, IncorrectSyntaxException {
            throw new IncorrectSyntaxException(actual);
    }
}
