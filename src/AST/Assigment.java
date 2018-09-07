package AST;

import Util.UsefulStrings;

/**
 *
 * identifier '=' expr ';'
 */
public class Assigment extends Statement{

    IAST var;
    Expr value;

    public Assigment(StatementType type, IAST var, Expr value) {
        super(type);
        this.var=var;
        this.value=value;
    }

    @Override
    public String toC() {
        return var.toC() + "=" + value.toC()+ ";";
    }

}
