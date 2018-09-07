package AST;

import AST.MetaData.MyDataType;
import AST.MetaData.TypeToC;
import Util.UsefulStrings;

/**
 * Created by hussam on 15.03.17.
 */
public class Variable implements IAST  {

    Expr value;
    IAST identifier;
    MyDataType type;

    public Variable(IAST identifier, MyDataType type) {
        this.identifier = identifier;
        this.type = type;
    }

    public Variable( IAST identifier, MyDataType type,Expr value) {
        this.value = value;
        this.identifier = identifier;
        this.type = type;
    }

    @Override
    public String toC() {
        if (value == null) {
            return TypeToC.toC(type) + identifier.toC() + ";" ;
        }
        else {
            return TypeToC.toC(type) + identifier.toC() + " = " + value.toC() + ";";
        }
    }

    public String paramToC() {
        return TypeToC.toC(type)+identifier.toC();
    }
}
