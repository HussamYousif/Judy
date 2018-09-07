package AST;


import Util.UsefulStrings;

import java.util.ArrayList;

public class Print extends Statement{
    String value;
    ArrayList<Expr> expressions;

    public Print( String value) {
        super(StatementType.PRINT);
        this.value = value;
        this.expressions = new ArrayList<Expr>();
    }

    public void addExpr(Expr e) {
        expressions.add(e);
    }

    @Override
    public String toC() {
        String expr = "";
        for(Expr e : expressions) {
            expr += ", " + e.toC() + " ";
        }
        return "printf(" + value + " " + expr + ")" + ";";
    }
}
