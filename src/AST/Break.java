package AST;


import Util.UsefulStrings;


public class Break extends Statement{

    public Break() {
        super(StatementType.BREAK);
    }

    @Override
    public String toC() {
        return  "break;";
    }
}
