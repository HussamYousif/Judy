package ErrorHandling;

import Lexing.Token;
import Util.TokenList;

/**
 * Created by hussam on 24.07.17.
 */
public class TypeException extends Exception{

    public TypeException(Token token) {
        super(String.format("New type error at line %d, position %d", token.getY(), token.getX() ));
    }

    public static void TypeException(TokenList tokens) throws TypeException {
        throw new TypeException(tokens.pop());
    }
}
