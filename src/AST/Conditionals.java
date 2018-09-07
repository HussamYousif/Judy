package AST;

import java.util.ArrayList;

/**
 * TODO IMPLEMENT THE toC method in all conditionals.
 */
public class Conditionals extends Statement{

    Statement ifStatement;
    ArrayList<Statement> elseIfs;
    Statement elseStatement;

    public Conditionals(Statement ifStatement, ArrayList<Statement> elseIfs) {
        super(StatementType.CONDITIONAL);
        this.ifStatement = ifStatement;
        this.elseIfs = elseIfs;
    }

    public Conditionals(Statement ifStatement, ArrayList<Statement> elseIfs, Statement elseStatement) {
        super(StatementType.CONDITIONAL);
        this.ifStatement = ifStatement;
        this.elseIfs = elseIfs;
        this.elseStatement = elseStatement;
    }


    @Override
    public String toC() {
        String additionalElseIfs = "";
        if (elseIfs != null) {
            for (Statement s : elseIfs) {
                additionalElseIfs += s.toC();
            }
        }
        return ifStatement.toC() + additionalElseIfs + elseStatement.toC();
    }

}
