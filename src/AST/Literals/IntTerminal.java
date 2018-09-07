package AST.Literals;

import AST.Term;

// Perhaps the naming of literal is unfortunate, should be int term.
public class IntTerminal extends Term {
    int val;

    public IntTerminal(int i) {
        super();
        val = i;
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
