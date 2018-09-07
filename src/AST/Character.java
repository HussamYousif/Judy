package AST;

/**
 * Created by Hussam on 8/24/2017.
 */
public class Character implements Expr{

    char val;

    public Character(String s) {

        // TODO This is stupid, stupid. Fix!
        this.val = s.charAt(1);
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
        return "\'" + val +"\'";
    }
}
