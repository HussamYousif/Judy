package AST;

import Lexing.Token;
import Lexing.TokenType;

/**
 * Created by hussa on 22.06.2017.
 */
public class BinOp implements Expr {

    private Operators op;

    public BinOp(Token type) {
        if (type.getTokenType() == TokenType.ADD)
            op = Operators.ADDITION;
        if (type.getTokenType() == TokenType.SUBTRACT)
            op = Operators.SUBTRACTION;
        if (type.getTokenType() == TokenType.MULTIPLY)
            op = Operators.MULTIPLICATION;
        if (type.getTokenType() == TokenType.DIVIDE)
            op = Operators.DIVISION;
        if(type.getTokenType() == TokenType.LESS)
            op = Operators.LESS;
        if (type.getTokenType() == TokenType.LEQ)
            op = Operators.LEQ;
        if (type.getTokenType() == TokenType.GREATER)
            op = Operators.GREATER;
        if (type.getTokenType() == TokenType.GEQ)
            op = Operators.GEQ;
        if (type.getTokenType() == TokenType.EQQ)
            op = Operators.EQQ;
        if (type.getTokenType() == TokenType.NEQ)
            op = Operators.NEQ;
        if (type.getTokenType() == TokenType.AND)
            op = Operators.AND;
        if (type.getTokenType() == TokenType.OR)
            op = Operators.OR;


        /* TODO ADD
        if (type.getTokenType() == TokenType.power)
            op = Operators.POWER;
            */

        if (type.getTokenType() == TokenType.LPARAM)
            op = Operators.LEFTPARANTHESIS;

        if (type.getTokenType() == TokenType.RPARAM)
            op = Operators.RIGHTPARANTHESIS;

    }

    public int getPrecedence() {
        if (op == Operators.ADDITION || op == Operators.SUBTRACTION)
            return 1;
        if (op == Operators.MULTIPLICATION || op == Operators.DIVISION)
            return 2;
        if (op == Operators.POWER)
            return 3;

        if (op == Operators.LEFTPARANTHESIS || op == Operators.RIGHTPARANTHESIS)
            return 0;

        return -1;
    }

    public Operators getOperator() {
        return this.op;
    }



    public String toC() {
        if (op == Operators.ADDITION)
            return "+";
        if (op == Operators.SUBTRACTION)
            return "-";
        if (op == Operators.MULTIPLICATION)
            return "*";
        if (op == Operators.DIVISION)
            return "/";
        if (op == Operators.LESS)
            return "<";
        if (op == Operators.LEQ)
            return "<=";
        if (op == Operators.GREATER)
            return ">";
        if (op == Operators.GEQ)
            return ">=";
        if (op == Operators.EQQ)
            return "==";
        if (op == Operators.NEQ)
            return "!=";
        if (op == Operators.AND)
            return "&&";
        if (op == Operators.OR)
            return "||";

        return null;
    }

    public static Operators getRightParenthesis() {
        return Operators.RIGHTPARANTHESIS;
    }

    @Override
    public boolean isTerm() {
        return false;
    }

    @Override
    public boolean isOperator() {
        return true;
    }



    public enum Operators {
        ADDITION, SUBTRACTION, MULTIPLICATION, DIVISION, POWER, LEFTPARANTHESIS, RIGHTPARANTHESIS, LESS, GREATER, LEQ,
        GEQ, EQQ, NEQ, AND, OR
    }

}
