package AST;


import AST.MetaData.MyDataType;
import AST.MetaData.TypeToC;
import Util.UsefulStrings;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Procedure extends AbstractProcedure {
    Identifier procedureName;
    ArrayList<IAST> param;
    MyDataType ret;

    public Procedure(Identifier functionName, ArrayList<IAST> param, MyDataType ret, ArrayList<Statement> body) {
        super(body);
        this.procedureName = functionName;
        this.param = param;
        this.ret = ret;
        this.body = body;
    }


    @Override
    public String toString() {
        List<String> par = paraToString();
        String para = "";

        for(String p : par) {
            para += p;
        }

        return procedureName.toString() + para + ret.toString();
    }

    // Not sure what this is, tbh.
    public ArrayList<String> paraToString() {
        // return this.param.stream().map(x -> toC()).collect(Collectors.toList());
        ArrayList<String> paramList = new ArrayList<String>();

        for (IAST p : param) {
            Variable v = (Variable) p;
            paramList.add(v.paramToC());
        }
        return paramList;
    }

    private String paraToC() {
        String paramString = "(";

        while (!param.isEmpty()) {
            Variable var =  (Variable) param.get(0);
            param.remove(0);
            if (param.isEmpty())
                paramString += var.paramToC();
            else
                paramString += var.paramToC() + ",";
        }
        paramString += ")";
        return paramString;
    }

    public String toC() {
        String paramString = paraToC();
        return TypeToC.toC(ret) + procedureName.toC() + paramString + " {"+ UsefulStrings.getNL() +
                UsefulStrings.addTabsForMultipleLineString(super.bodyToC()) +
                "}" +
                UsefulStrings.getNL();
    }
}