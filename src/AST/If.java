package AST;

import AST.Expr;
import AST.Statement;
import AST.StatementType;
import Util.UsefulStrings;

import java.util.ArrayList;


public class If extends Statement {

    private Expr condition;
    private ArrayList<Statement> block;


    public If( Expr condition) {
        super(StatementType.IF);
        this.condition = condition;
        block = new ArrayList<Statement>();
    }

    public void addDoStatement(Statement s) {
        this.block.add(s);
    }

    @Override
    public String toC() {

        String body = "";
        for (Statement s: block) {
            body += s.toC();
        }
        return  "if (" + condition.toC() + ") {" + UsefulStrings.getNL() +
                 UsefulStrings.addTabsForMultipleLineString(body) + "}";
    }
}