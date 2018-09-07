package AST.Literals;

import AST.Term;

public class FloatTerminal extends Term {

    double val;


    public FloatTerminal(double val) {
        this.val = val;
    }

    @Override
    public String toC() {
        return String.valueOf(val);
    }

    @Override
    public Object getVal() {
        return val;
    }

}
