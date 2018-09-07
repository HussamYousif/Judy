package AST;

/**
 *
 */
public class Number implements Expr {

    public int value;

    public Number(int value) {
        this.value = value;
    }


    @Override
    public boolean isTerm() {
        return true;
    }

    @Override
    public boolean isOperator() {
        return false;
    }

    public int value() {
        return this.value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public String toC() {
        return Integer.toString(value);
    }
}
