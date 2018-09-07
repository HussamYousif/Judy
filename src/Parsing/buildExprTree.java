package Parsing;

import AST.BinExpr;
import AST.BinOp;
import AST.Expr;

import java.util.List;

public class buildExprTree {


    // Recursive Thingy
    public static Expr build(List<Expr> l) {
        Expr next = l.remove(0);
        Expr e;

        if (next.isTerm()) {
            return next;
        }

        // Recurse on left and right.
        else {
            Expr left = build(l);
            Expr right = build(l);
            e = new BinExpr((BinOp) next, right, left);
        }

        return e;
    }

}
