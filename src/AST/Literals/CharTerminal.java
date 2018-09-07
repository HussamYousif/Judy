package AST.Literals;

import AST.Term;

public class CharTerminal extends Term {

    char val;

    public CharTerminal(char val) {
        this.val = val;
    }

    @Override
    public String toC() {
        return " \'" + String.valueOf(val) + "\'";
    }

    @Override
    public Object getVal() {
        return val;
    }

}
