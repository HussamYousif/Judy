package AST;

import Util.UsefulStrings;

import java.util.ArrayList;

/**
 * Events are procedures who's execution require a an expression to evaluate to not o (true).
 *
 *
 */
public class Event {

    private Expr trigger;
    private IAST id;
    private ArrayList<Statement> statements;

    public Event(IAST id, Expr trigger) {
        this.trigger = trigger;
        this.id = id;
        statements = new ArrayList<Statement>();
    }

    public void addStatement(Statement s) {
        this.statements.add(s);
    }

    /**
     * Event: 1>2; myEvent:
     *  [StatementList]
     * End
     */
    public String toC() {
        String body = "";
        for(Statement s : statements)
            body += s.toC() + UsefulStrings.getNL();

        return "void " + id + "() {" + UsefulStrings.getNL()+
                UsefulStrings.addTabsForMultipleLineString(body) +
                "}" + UsefulStrings.getNL();
    }

    // Used for the main loop in C.
    public String toMain() {
        return "if (" + trigger.toC() + ") {" + UsefulStrings.getNL() +
                UsefulStrings.getTab() + UsefulStrings.getTab() + id.toC() + "();" + UsefulStrings.getNL() +
                UsefulStrings.getTab() + "}";
    }
}
