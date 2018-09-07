package AST;



/**
 * Created by Hussam on 8/24/2017.
 */
public class Decimal implements Expr {

    Double val;

    public Decimal(Double val) {
        this.val = val;
    }

    @Override
    public boolean isTerm() {
        return true;
    }

    @Override
    public boolean isOperator() {
        return false;
    }

    @Override
    public String toC() {
        return val.toString();
    }
}
