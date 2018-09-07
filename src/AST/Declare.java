package AST;

import AST.MetaData.MyDataType;
import AST.MetaData.TypeToC;
import Util.UsefulStrings;

/*
import javax.jws.soap.SOAPBinding;
import javax.swing.plaf.nimbus.State;
import java.lang.reflect.Type;
*/

/**
 * Created by Hussam on 7/13/2017.
 */
public class Declare extends Statement{

    private MyDataType type;
    private IAST id;
    private IAST value;


    public Declare( MyDataType type, IAST id) {
        super(StatementType.DECLARE);
        this.type = type;
        this.id = id;
    }

    public Declare( MyDataType type, IAST id, IAST value) {
        super(StatementType.DECLAREASSIGN);
        this.type = type;
        this.id = id;
        this.value=value;
    }

    @Override
    public String toC() {
        if (super.getType() == StatementType.DECLARE) {
            return TypeToC.toC(type) + id.toC()  + ";" ;
        }
        else
            return  TypeToC.toC(type)+id.toC() + " = " +value.toC() + ";";
    }
}
