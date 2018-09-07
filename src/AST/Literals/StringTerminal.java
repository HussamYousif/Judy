package AST.Literals;

import AST.Term;

public class StringTerminal extends Term {

    String val;

    public StringTerminal(String val) {
        this.val = val;
    }

    @Override
    public String toC() {
        return String.valueOf(val);
    }

    @Override
    public Object getVal() {
        return "\"" +  val +"\"";
    }
}
