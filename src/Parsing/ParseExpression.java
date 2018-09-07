package Parsing;

import AST.*;
import AST.Literals.*;
import ErrorHandling.TokenMisMatchException;
import Lexing.Token;
import Lexing.TokenType;
import Util.TokenList;

import java.util.*;

/**
 * Shunting Yard Algorithm, stolen from Wikipedia.
 */
public class ParseExpression {


    private static List<TokenType> operatorList;

    // First time I use static.
    static{
        operatorList = new ArrayList<TokenType>();
        operatorList.addAll(Arrays.asList(TokenType.ADD, TokenType.SUBTRACT, TokenType.MULTIPLY,
                TokenType.DIVIDE, TokenType.LPARAM, TokenType.RPARAM, TokenType.LEQ,
                TokenType.LESS, TokenType.GEQ, TokenType.GREATER, TokenType.EQQ, TokenType.NEQ, TokenType.OR,
                TokenType.AND));
    }


    public static Expr parseExpr(TokenList tokens, TokenType endToken) throws TokenMisMatchException {
        Queue<Expr> output;

        output = new LinkedList<Expr>();
        Stack<BinOp> s = new Stack<BinOp>();

        while (!tokens.isEmpty() && tokens.peek().getTokenType() != endToken) {
            Token next = tokens.pop();

            if (next.getTokenType() == endToken)
                break;

            if (next.getTokenType() == TokenType.DECIMAL) {
                output.add(parseDecimal(next));
                continue;
            }

            if (next.getTokenType() == TokenType.NUMBER) {
                output.add(parseNumber(next));
                continue;
            }


            // FunctionCall
            if (next.getTokenType() == TokenType.IDENTIFIER && tokens.peek().getTokenType() ==TokenType.LPARAM) {
                Expr id = parseIdentifier(next);
                output.add(ParseFunctionCall.parse(tokens, id));
                continue;
            }


            // Identifier.
            if (next.getTokenType() == TokenType.IDENTIFIER) {
                output.add(parseIdentifier(next));
                continue;
            }

            // String.
            if (next.getTokenType() == TokenType.STRING) {
                output.add(parseString(next));
                continue;
            }
            // Character.
            if (next.getTokenType() == TokenType.CHARACTER) {
                output.add(parseCharacter(next));
                continue;
            }


            // Function Identifier.

            // Operator that is not a parenthesis.
            if (isOperator(next) && !isLeftParan(next) && !isRightParan(next)) {
                BinOp currentOp = parseOperator(next);

                    while (!s.isEmpty() && s.peek().getPrecedence() >= currentOp.getPrecedence()) {
                            output.add(s.pop());
                    }

                    s.add(currentOp);
                    continue;
            }


            if (isLeftParan(next)) {
                s.add(parseOperator(next));
                continue;
            }

            // Right paren pops operators from s to output till it finds a left parenthesis.
            if (isRightParan(next)) {

                while(!s.isEmpty() && s.peek().getOperator() != BinOp.Operators.LEFTPARANTHESIS) {
                    output.add(s.pop());
                }

                if (s.isEmpty()) {
                    throw new TokenMisMatchException("Parenthesis mismatch!");
                }

                // Pop left paren, if there are none then paren mismatch.
                s.pop(); // Pop the lef parenthesis.

                continue; // For consistency.

            }
        }

        // No need to build tree.
        if (output.size() == 1) {
            return output.remove();
        }

        // s -> output
        // Fun fact: s.streams.foreach(x -> output.add(x)); adds the element in the reverse order.
        while (!s.empty()) {
            output.add(s.pop());
        }

        List<Expr> retExpr = new ArrayList<Expr>();

        while (!output.isEmpty()) {
            retExpr.add(output.remove());
        }

        // prefix > postfix.
        Collections.reverse(retExpr);

        return buildExprTree.build(retExpr);
    }

    private static Expr parseString(Token next) {
        return new StringTerminal(next.getData());
    }


    private static boolean isRightParan(Token next) {
        return next.getTokenType() == TokenType.RPARAM;
    }

    private static boolean isLeftParan(Token next) {
        return next.getTokenType() == TokenType.LPARAM;
    }


    // Determines whether the token is an operator or not.
    private static boolean isOperator(Token token) {
        return operatorList.contains(token.getTokenType());
    }


    private static BinOp parseOperator(Token token) {
        return new BinOp(token);
    }

    private static Expr parseNumber(Token token) {
        return new IntTerminal(Integer.parseInt(token.getData()));
    }

    private static Expr parseIdentifier(Token token) {
        return new IdentifierTerminal(token.getData());
    }

    private static Expr parseDecimal(Token token) {
        return new FloatTerminal(Double.parseDouble(token.getData()));
    }

    private static Expr parseCharacter(Token next) {
        return new CharTerminal(next.getData().charAt(1));
    }

    @Deprecated
    // The naive implementation of expressions.
    // Reads from left to right till ';'
    public static Expr uncoolParseExpr(TokenList tokens) throws TokenMisMatchException {

        ArrayList<Expr> expression = new ArrayList<Expr>();

        while(tokens.peek().getTokenType() != TokenType.SEMICOLON && tokens.peek().getTokenType() != TokenType.DO &&
                tokens.peek().getTokenType() != TokenType.ELSEIF && tokens.peek().getTokenType() != TokenType.ELSE) {


            if (tokens.isFunctionCall()) {
                //expression.add(parseFunCall(tokens));
                continue;
            }

            Token next = tokens.pop();

            if (isOperator(next)) {
                expression.add(new BinOp(next));
                continue;
            }

            if (next.getTokenType() == TokenType.DECIMAL) {
                expression.add(parseDecimal(next));
                continue;
            }

            if (next.getTokenType() == TokenType.NUMBER ) {
                expression.add(parseNumber(next));
                continue;
            }

            if (next.getTokenType() == TokenType.IDENTIFIER) {
                expression.add(parseIdentifier(next));
                continue;
            }

            if (next.getTokenType() == TokenType.CHARACTER) {
               // expression.add(new Character(tokens.pop().getData()));
                continue;
            }
        }

        //Expr result = new BinExpr(expression);

        return null;
    }

}
