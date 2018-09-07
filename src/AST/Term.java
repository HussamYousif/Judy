package AST;

public class Term implements Expr {


    @Override
    public boolean isTerm() {
        return true;
    }

    @Override
    public boolean isOperator() {
        return false;
    }

    @Override
    public String toC() {
        return null;
    }

    public Object getVal() {
        return null;
    }

}
