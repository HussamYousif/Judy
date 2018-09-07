package AST;

import Util.UsefulStrings;

import java.util.ArrayList;

/**
 * Created by hussam on 22.03.2017.
 *
 * The initial procedure, entry point of the program. Should be called inside main by the c file.
 */
public class Start extends AbstractProcedure {

    public Start() {
        super();
    }

    public void addProcedure(Statement s) {
        super.addStatement(s);
    }


    public String toC() {
        // I really should mind the access modifiers. // TODO encapsulate code.
        String result = "void start() {" + UsefulStrings.getNL();

        for (Statement s : super.body) {
            result += UsefulStrings.addTabsForMultipleLineString(s.toC());
        }
        result += "}" + UsefulStrings.getNL();

        return result;
    }
}
