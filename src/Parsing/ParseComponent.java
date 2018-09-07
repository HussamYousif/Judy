package Parsing;

import AST.Component;
import AST.ComponentBody;
import AST.Identifier;
import ErrorHandling.TokenMisMatchException;
import Lexing.TokenType;
import Util.TokenList;

/**
 * Component NyComponent:
 *
 * Some code
 *
 * End
 *
 */
public class ParseComponent {

    /*
        Component [Identifier]

        End
     */
    public static Component parse(TokenList tokens) throws Exception {

        // Should be a 'Component' (Some identifier)
        TokenMisMatchException.awaitedToken(TokenType.COMPONENT, tokens.pop());
        TokenMisMatchException.awaitedToken(TokenType.IDENTIFIER, tokens.peek());

        Identifier id = new Identifier(tokens.pop().getData());

        Component component = new Component(id);

        TokenMisMatchException.awaitedToken(TokenType.COLON, tokens.pop());

        ComponentBody body =  ParseComponentBody.parse(tokens);

        component.addBody(body);

        return component;

    }
}
