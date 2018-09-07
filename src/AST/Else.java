package AST;

import Util.UsefulStrings;

import java.util.ArrayList;


public class Else extends Statement{

    ArrayList<Statement> block;

    public Else() {
        super(StatementType.ELSE);
        block = new ArrayList<Statement>();
    }

    public void addstatement(Statement s) {
        block.add(s);
    }

    @Override
    public String toC() {
        if (this.equals(null))
            return "";
        String body = "";
        for (Statement s : block) {
            body += s.toC();
        }
        return UsefulStrings.getTab() + "else {" + UsefulStrings.getNL()+ UsefulStrings.addTabsForMultipleLineString(body) + "}" + UsefulStrings.getNL();
    }
}
