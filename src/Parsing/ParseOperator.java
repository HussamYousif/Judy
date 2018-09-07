package Parsing;

import AST.BinOp;
import AST.Expr;
import Lexing.Token;

/**
 * Created by hussa on 23.06.2017.
 */
public class ParseOperator {

    public static Expr parse(Token token) {
        return new BinOp(token);
    }
}
