package AST;


    public interface Expr extends IAST {

        boolean isTerm();

        boolean isOperator();
    }
