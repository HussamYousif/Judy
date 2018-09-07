package AST;

import AST.MetaData.MyDataType;
import Util.UsefulStrings;

/**
 * Created by hussam on 28.04.17.
 */
public class ReturnStatement extends Statement{

    private IAST returnExpr;
    private MyDataType returnType;

    public ReturnStatement(StatementType type, IAST returnExpr) {
        super(type);
        this.returnExpr=returnExpr;
    }

    @Override
    public String toC() {
        return "return " + returnExpr.toC() + ";";
    }

}
