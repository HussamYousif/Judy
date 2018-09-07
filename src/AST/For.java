package AST;

import Util.UsefulStrings;

/**
 */
public class For extends Loops {

    private Statement init;
    private Statement increment;

    public For(Statement init,IAST condition, Statement increment) {
        super(condition);
        this.init=init;
        this.increment=increment;
    }


    public Statement getInit() {
        return init;
    }


    public Statement getIncrement() {
        return increment;
    }

    public String toC() {
        String update = increment.toC();

        if (update.substring(update.length() - 1).equals(";")) {
            StringBuilder sb = new StringBuilder(update);
            sb.deleteCharAt(update.length() - 1);

            update = sb.toString();
        }


        return  "for(" + init.toC() + conditionToC() + ";" + update + ") {" + UsefulStrings.getNL() +
                UsefulStrings.addTabsForMultipleLineString(super.bodyToC()) 
                + "}";
    }
}
