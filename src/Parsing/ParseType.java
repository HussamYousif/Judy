package Parsing;

import AST.MetaData.MyDataType;
import Lexing.Token;
import Lexing.TokenType;
import Util.TokenList;


public class ParseType {


    public static MyDataType parse(TokenList tokens) throws Exception {
        Token t = tokens.pop();
        if (t.getTokenType() == TokenType.INT) {
            return MyDataType.INT;
        }
        else if (t.getTokenType() == TokenType.DOUBLE)
            return MyDataType.DOUBLE;
        else if (t.getTokenType() == TokenType.CHAR)
            return MyDataType.CHAR;
        else if (t.getTokenType() == TokenType.LONG)
            return MyDataType.LONG;
        else if (t.getTokenType() == TokenType.FLOAT)
            return MyDataType.FLOAT;
        else if (t.getTokenType() == TokenType.SHORT)
            return MyDataType.SHORT;
        else if (t.getTokenType() == TokenType.VOID)
            return MyDataType.VOID;
        else
            throw new Exception("Undefined type" + t.getTokenType().toString());
    }
}
