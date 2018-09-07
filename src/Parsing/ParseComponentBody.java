package Parsing;

import AST.ComponentBody;
import AST.Event;
import AST.MetaData.IsTypeToken;
import AST.Procedure;
import Lexing.TokenType;
import Util.TokenList;


public class ParseComponentBody {

    // TODO Add start and Events.
    public static ComponentBody parse(TokenList tokens) throws Exception {
        ComponentBody body = new ComponentBody();

        while (tokens.peek().getTokenType() != TokenType.END) {
            int runs = 0;

            // Parse Start.
            if (tokens.peek().getTokenType() == TokenType.START) {
                body.addStart(ParseStart.parse(tokens));
                continue;
            }

            // Parse imports.
            if (tokens.peek().getTokenType() == TokenType.IMPORTS) {
                body.addImport(ParseImports.parse(tokens));
                continue;
            }

            // Parse Procedures.
            if (tokens.isFunctionCall()) {

                Procedure p = ParseProcedure.parse(tokens);
                body.addProcedure(p);
                continue;
            }

            // Global Variables.
            if (IsTypeToken.isType(tokens.peek())) {
               body.addGlobal(ParseGlobal.parse(tokens));
                continue;
            }

            // Parse Events.
            if (tokens.peek().getTokenType() == TokenType.TRIGGER) {
                Event e = ParseEvent.parse(tokens);
                body.addEvent(e);
                continue;
            }

            runs++;

            if (runs >= 1) {
                throw new Exception("Eternal loop at Component body");
            }
        }

        return body;
    }
}
