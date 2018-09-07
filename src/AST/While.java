package AST;

import Util.UsefulStrings;


public class While extends Loops {


    public While(IAST condition) {
        super(condition);
    }

    public void addStatement(Statement s) {
        super.addStatement(s);
    }


    @Override
    public String toC() {
        return UsefulStrings.getTab() + "while (" + super.conditionToC() +") {" + UsefulStrings.getNL() +
                UsefulStrings.addTabsForMultipleLineString(super.bodyToC()) +
                UsefulStrings.getTab() + "}";
    }
}
