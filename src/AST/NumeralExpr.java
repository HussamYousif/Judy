package AST;

import java.util.ArrayList;
import java.util.Queue;

/**
 * Created by Hussam on 7/13/2017.
 */
public class NumeralExpr implements IAST {

    private Queue<Expr> output;


    public NumeralExpr(Queue<Expr> output) {
        this.output=output;
    }

    @Override
    public String toC() {
        return null;
    }

    public Queue<Expr> getOutput() {
        return this.output;
    }
}
