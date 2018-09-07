package AST;

import Util.UsefulStrings;

import java.util.ArrayList;

/**
 * Created by Hussam on 7/28/2017.
 */
public class ElseIf extends Statement{

    private Expr condition;
    private ArrayList<Statement> block;

    public ElseIf(Expr condition) {
        super(StatementType.ELSEIF);
        this.condition=condition;
    }

    public void addStatement(Statement s) {
        this.block.add(s);
    }

    @Override
    public String toC() {
        if (this.equals(null))
            return "";
        String body = "";
        for(Statement s : block) {
            body += s.toC();
        }
        return "else if (" + condition.toC() + ") {" +UsefulStrings.getNL() + UsefulStrings.addTabsForMultipleLineString( body ) + "}";
    }
}
