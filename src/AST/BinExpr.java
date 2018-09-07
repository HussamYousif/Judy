package AST;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

import static AST.BinOp.Operators.ADDITION;

/**
 */
public class BinExpr implements Expr {

    BinOp op;
    Expr left;
    Expr right;


    public BinExpr(BinOp op, Expr left, Expr right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean isTerm() {
        return false;
    }

    @Override
    public boolean isOperator() {
        return false;
    }

    public Expr getLeftExpr() {
        return left;
    }

    public Expr getRightExpr() {
        return right;
    }

    @Override
    public String toC() {
        String result =  "(" + left.toC() + op.toC() + right.toC() + ")";
        return result;
    }

    public BinOp getOp() {
        return op;
    }
}
