package AST.MetaData;


import ErrorHandling.TypeException;
import Lexing.TokenType;
import Util.TokenList;

public class TypeToC {


    public static String toC(MyDataType type) {
        if (type == MyDataType.INT)
            return "int ";
        else if (type == MyDataType.CHAR)
            return "char ";
        else if (type == MyDataType.DOUBLE)
            return "double ";
        else if (type == MyDataType.LONG)
            return "long ";
        else if (type == MyDataType.FLOAT)
            return "float ";
        else if (type == MyDataType.SHORT)
            return "short ";
        else if (type == MyDataType.VOID)
            return "void ";
        return null;
    }

}
