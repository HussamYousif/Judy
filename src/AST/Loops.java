package AST;


import Util.UsefulStrings;

import java.util.ArrayList;

public abstract class Loops extends Statement {

    private IAST condition;
    private ArrayList<Statement> body;

    public Loops(IAST condition) {
        super(StatementType.LOOP);
        this.condition=condition;
        this.body = new ArrayList<Statement>();
    }

    public IAST getCondition() {
        return condition;
    }

    public ArrayList<Statement> getBody() {
        return body;
    }

    public void addStatement(Statement s) {
        body.add(s);
    }

    public String conditionToC() {
        return condition.toC();
    }

    public String bodyToC() {
        String result = "";
        for(Statement s : body) {
            result += UsefulStrings.addTabsForMultipleLineString(s.toC());
        }
        return result;
    }
}
