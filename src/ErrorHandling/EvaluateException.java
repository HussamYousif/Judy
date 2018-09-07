package ErrorHandling;

import Lexing.Token;

/**
 * Created by hussam on 23.05.17.
 *
 * For when people try to evaluate identifiers and such.
 */
public class EvaluateException extends Exception {

    public EvaluateException(Token actual) {
        super(String.format("Unevaluatable token: %s", actual.getTokenType().toString()));
    }
}
