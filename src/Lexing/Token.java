package Lexing;

/**
 * Created by hussam on 09.02.17.
 */
public class Token {

    private TokenType type;
    private String data;
    int xPos;
    int yPos;

    public Token(TokenType type, String data, int x, int y) {
        this.type = type;
        this.data = data;
        this.xPos = x;
        this.yPos = y;
    }

    public int getY() {
        return this.yPos;
    }

    public int getX() {
        return this.xPos;
    }

    public String getData() {
        return this.data;
    }

    public TokenType getTokenType() {
        return this.type;
    }

    @Override
    public String toString() {

        return String.format("(%s %s)", type.name(), data);
    }
}
