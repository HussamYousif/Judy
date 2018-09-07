package Parsing;

import AST.*;
import AST.Identifier;
import AST.MetaData.MyDataType;
import AST.Statement;
import AST.Variable;
import ErrorHandling.TokenMisMatchException;
import Lexing.TokenType;
import Util.TokenList;

import java.util.ArrayList;

/**
 */
public class ParseProcedure {


    /**
     *
     * Identifier '(' [param] ')' ->
     *
     */
    public static Procedure parse(TokenList tokens) throws Exception {
        TokenMisMatchException.awaitedToken(TokenType.IDENTIFIER, tokens.peek());

        Identifier id = new Identifier(tokens.pop().getData());

        ArrayList<IAST> params = new ArrayList<IAST>();

        // Parameters.
        TokenMisMatchException.awaitedToken(TokenType.LPARAM, tokens.pop());
        while (tokens.peek().getTokenType() != TokenType.RPARAM) {

            if (tokens.peek().getTokenType() == TokenType.COMMA)
                tokens.pop();

            TokenMisMatchException.awaitType(tokens.peek());
            MyDataType type = ParseType.parse(tokens);

            IAST varId = ParseIdentifier.parse(tokens);
            IAST aux = new Variable(varId, type);

            params.add(aux);
        }
        tokens.pop(); // Discarding right parenthesis.

        TokenMisMatchException.awaitedToken(TokenType.ARROW, tokens.pop());

        MyDataType retType = ParseType.parse(tokens);

        TokenMisMatchException.awaitedToken(TokenType.COLON, tokens.pop());

        ArrayList<Statement> statements = new ArrayList<Statement>();
        while(tokens.peek().getTokenType() != TokenType.END) {
            Statement statement = ParseStatement.parse(tokens);
            statements.add(statement);
        }

        // TODO Ensure that the variable returned is of the correct type.
        TokenMisMatchException.awaitedToken(TokenType.END, tokens.pop());


        return new Procedure(id, params, retType, statements);

    }
}
