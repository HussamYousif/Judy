package Util;

import AST.Expr;
import Lexing.Token;
import Lexing.TokenType;
import Lexing.Tokenizor;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hussam on 15.03.17.
 *
 * Just to make things a bit easier on my self.
 */
public class TokenList {

    List<Token> tokens;

    public TokenList(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public TokenList(String input) {
        this.tokens = Tokenizor.lex(input);
    }

    public List<Token> getList() {
        return this.tokens;
    }

    public Token pop() {
        Token token = tokens.get(0);
        tokens.remove(0);
        return token;
    }

    public Token peek() {
        return tokens.get(0);
    }

    public Token lookAhead(int i) {
        return tokens.get(i);
    }

    public boolean isEmpty() {
        return this.tokens.isEmpty();
    }

    public void showTokens() {
        tokens.forEach(x -> System.out.println(x));
    }


    public boolean isFunctionCall() {
        try {
            // If next two tokens are [identifier] and [(]
            return (this.tokens.get(0).getTokenType() == TokenType.IDENTIFIER && this.tokens.get(1).getTokenType() == TokenType.LPARAM);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public boolean isEmptyFunctionCall() {
        return ((this.tokens.get(0).getTokenType() == TokenType.IDENTIFIER) && (this.tokens.get(1).getTokenType() == TokenType.LPARAM) &&
                (this.tokens.get(2).getTokenType() == TokenType.RPARAM));
    }

}
