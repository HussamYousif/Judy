package Parsing;

import AST.Expr;
import AST.FunctionCall;
import ErrorHandling.TokenMisMatchException;
import Lexing.Token;
import Lexing.TokenType;
import Util.TokenList;

public class ParseFunctionCall {



    public static FunctionCall parse(TokenList tokens, Expr id) throws TokenMisMatchException {

        // '('
        TokenMisMatchException.awaitedToken(TokenType.LPARAM, tokens.pop());

        FunctionCall f = new FunctionCall(id);

        // Empty params.
        if (tokens.peek().getTokenType() == TokenType.RPARAM) {
            tokens.peek(); // ')'
            return new FunctionCall(id);
        }


        boolean finishParamParsing = false;
        while (!finishParamParsing) {


            if (lastParam(tokens)) {
                f.addParam(ParseExpression.parseExpr(tokens, TokenType.RPARAM));
                tokens.pop();
                finishParamParsing = true;
            }


            else {
                f.addParam(ParseExpression.parseExpr(tokens, TokenType.COMMA));
                TokenMisMatchException.awaitedToken(TokenType.COMMA, tokens.pop());
            }
        }

        return f;
    }

    public static boolean lastParam(TokenList tokens) {
        int lparam = 0;
        for(Token t : tokens.getList()) {

            // In case of a paranthesized expression or a function call.
            if (t.getTokenType() == TokenType.LPARAM) {
                lparam++;
            }

            if (t.getTokenType() == TokenType.COMMA && lparam == 0) {
                return false;
            }


            if (t.getTokenType() == TokenType.RPARAM && lparam == 0) {
                return true;
            }

            if (t.getTokenType() == TokenType.RPARAM) {
                lparam--;
            }
        }
        return false;
    }
}
