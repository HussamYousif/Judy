package AST;

/**
 *
 * Every statement class is going to extend this.
 *
 * In higndsight no reason to have it as an abstract class
 * could have used an interface instead.
 */
public abstract class Statement {

    StatementType type;

    public Statement(StatementType type) {
        this.type = type;
    }

    public Statement() {
    }

    public StatementType getType() {
        return this.type;
    }

    public String toC() {
        return null;
    }



}
