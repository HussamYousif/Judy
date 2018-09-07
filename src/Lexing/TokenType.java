package Lexing;


public enum TokenType {

    COMPONENT("Component"),
    TRIGGER("Trigger:"),
    IF("If"),
    IMPORTS("Import"),
    ELSEIF("Elseif"),
    FOR("For"),
    WHILE("While"),
    ELSE("Else"),
    RETURN("Return"),
    PRINT("Print"),
    START("Start"),
    END("End"),
    CONTINUE("Continue"),
    BREAK("Break"),
    CHAR("Char"),
    SHORT("Short"),
    INT("Int"),
    LONG("Long"),
    FLOAT("Float"),
    DOUBLE("Double"),
    DO("Do"), // Must be behind Double!
    VOID("Void"),
    AND("And"),
    OR("Or"),
    EQQ("=="),
    NEQ("!="),
    EQ("="),
    COLON(":"),
    COMMA(","),
    LEQ("<="),
    GEQ(">="),
    LESS("<"),
    GREATER(">"),
    LBRACKET("\\{"),
    RBRACKET("}"),
    LPARAM("\\("),
    RPARAM("\\)"),
    SEMICOLON("[;]"),
    ARROW("->"),
    NEWLINE("[\r\n|\r|\n]"),
    SUBTRACT("-"),
    DECIMAL("[0-9]+\\.[0-9]+"),
    NUMBER("-?[0-9]+"),
    IDENTIFIER("[A-Za-z][A-Za-z0-9]*"),
    ADD("\\+"),
    MULTIPLY("\\*"),
    DIVIDE("/"),
    CHARACTER("'([^']*)'"),
    STRING("\"(.*?)\""),
    WHITESPACE("[ \t\f\r\n]+");
    //ERROR(".+");

    public final String pattern;

    private TokenType(String pattern) {

        this.pattern = pattern;
    }
}
