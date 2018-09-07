package AST;


import Util.UsefulStrings;

public class Imports {
    String lib;

    public Imports(String lib) {
        this.lib=lib;
    }

    // TODO FULFILL.
    public String toC() {
        return "#include" +  " " + lib + UsefulStrings.getNL();
    }


}
