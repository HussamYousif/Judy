package AST;


import Util.UsefulStrings;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FunctionCall extends Statement implements Expr {

    private Expr id;
    private List<Expr> param; // Null for no parameters.
    boolean isStmt;

    public FunctionCall(Expr id, List<Expr> param, boolean isStmt) {
        this.id = id;
        this.param = param;
        this.isStmt = isStmt;
    }

    public FunctionCall(Expr id ) {
        super();
        this.id = id;
        this.param = null;
        this.isStmt = false;
    }


    public void setStmt() {
        this.isStmt = true;
    }

    public void addParam(Expr e) {

        if (this.param== null) {
            this.param = new ArrayList<Expr>();
        }

        this.param.add(e);
    }

    @Override
    public boolean isTerm() {
        return true;
    }


    @Override
    public boolean isOperator() {
        return false;
    }

    public Expr getId() {
        return id;
    }

    public boolean emptyParameters() {
        return this.param == null;
    }

    @Override
    public String toC() {
        String result = "";
        if (emptyParameters()) {
            result = id.toC() + "()";
        }

        else {
            String p = String.join(",", param.stream().map(x -> x.toC()).collect((Collectors.toList())));
            result = id.toC() +"(" + p + ")"; // expression or part of an expression.
        }

        if (isStmt)
            result +=  ";"; // Every statement ends with ';'
        return result;
    }
}
