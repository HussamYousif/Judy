package AST;

import Util.UsefulStrings;

import java.util.ArrayList;


public abstract class AbstractProcedure {

    ArrayList<Statement> body;

    // Why does this work when the list is not initialized. this should throw an exception, but it's not..
    public AbstractProcedure(ArrayList<Statement> body) {
        this.body = body;
    }

    public AbstractProcedure() {
        this.body = new ArrayList<Statement>();
    }

    public void addStatement(Statement s) {
        body.add(s);
    }

    public String bodyToC() {
        String bodyString  = "" ;

        for(Statement s : body) {
            bodyString += UsefulStrings.getTab() + s.toC() + UsefulStrings.getNL();
        }

        return bodyString;
    }

}
