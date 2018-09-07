package Parsing;

import AST.Expr;
import AST.IAST;
import AST.MetaData.GetTypeFromToken;
import AST.MetaData.MyDataType;
import AST.Variable;
import ErrorHandling.IncorrectSyntaxException;
import ErrorHandling.TokenMisMatchException;
import ErrorHandling.TypeException;
import Lexing.TokenType;
import Util.TokenList;


public class ParseGlobal {


    public static Variable parse(TokenList tokens) throws TypeException, TokenMisMatchException, IncorrectSyntaxException {
        MyDataType type = GetTypeFromToken.getType(tokens);
        IAST id = ParseIdentifier.parse(tokens);

        if (tokens.peek().getTokenType() == TokenType.SEMICOLON) {
            tokens.pop(); // The semicolon.
            return new Variable(id, type);
        }
        // Declare + assigment.
        else if (tokens.peek().getTokenType() == TokenType.EQ) {
            TokenMisMatchException.awaitedToken(TokenType.EQ , tokens.pop());
            Expr e = ParseExpression.parseExpr(tokens, TokenType.SEMICOLON);
            tokens.pop(); // The semicolon.
            return new Variable(id, type, e);
        }

        // Unexpected token, incorrect syntax. Should end the parsing here.
        else {
            IncorrectSyntaxException.throwSyntaxException(tokens.peek());
        }

        // Should never reach this point.
        return null;
    }
}
