package AST;


import Util.UsefulStrings;

public class Continue extends Statement{


    public Continue() {
        super(StatementType.CONTINUE);
    }

    @Override
    public String toC() {
        return  "continue;";
    }
}
