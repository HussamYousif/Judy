package AST;

import Util.UsefulStrings;

import java.util.ArrayList;


public class Component {

    private Identifier id;
    private ComponentBody body;

    public Component(Identifier id) {
        this.id = id;
        body = new ComponentBody();
    }

    public Identifier getId() {
        return id;
    }

    public ComponentBody getBody() {
        return body;
    }

    public void addBody(ComponentBody body) {
        this.body = body;
    }

    public String toC() {
        String cFile = "";

        cFile += "// This is the " + id.toC() + " Component" + UsefulStrings.getNL();

        cFile += body.toC();

        return cFile;
    }
}
