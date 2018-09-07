package AST.Literals;

import AST.Term;

public class IdentifierTerminal extends Term {

    String value;

    public IdentifierTerminal(String value) {
        this.value = value;
    }

    @Override
    public String toC() {
        return value;
    }

    public Object getVal() {
        return value;
    }
}
