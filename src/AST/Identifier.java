package AST;

/**
 */
public class Identifier implements Expr {

    String identifier;

    public Identifier(String identifier) {
        this.identifier = identifier;
    }

    public String getValue() {
        return this.identifier;
    }

    public String toString() {
        return this.identifier;
    }

    @Override
    public String toC() {
        return identifier;
    }

    @Override
    public boolean isTerm() {
        return true;
    }

    @Override
    public boolean isOperator() {
        return false;
    }

}
