package AST.MetaData;

import ErrorHandling.TypeException;
import Lexing.TokenType;
import Util.TokenList;

public class GetTypeFromToken {

    // TODO add datatypes.
    public static MyDataType getType(TokenList tokens) throws TypeException {
        if (tokens.peek().getTokenType() == TokenType.INT){
            tokens.pop();
            return MyDataType.INT;
        }

        else if (tokens.peek().getTokenType() == TokenType.SHORT) {
            tokens.pop();
            return MyDataType.SHORT;
        }

        else if (tokens.peek().getTokenType() == TokenType.CHAR) {
            tokens.pop();
            return MyDataType.CHAR;
        }
        else if (tokens.peek().getTokenType() == TokenType.LONG) {
            tokens.pop();
            return MyDataType.LONG;
        }
        else if (tokens.peek().getTokenType() == TokenType.FLOAT) {
            tokens.pop();
            return MyDataType.FLOAT;
        }
        else if (tokens.peek().getTokenType() == TokenType.DOUBLE) {
            tokens.pop();
            return MyDataType.DOUBLE;
        }

        else {
            TypeException.TypeException(tokens);
            return null;
            //tokens.pop();
            //return MyDataType.VOID;
        }
    }
}
