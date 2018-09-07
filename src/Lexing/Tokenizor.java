package Lexing;
//import com.sun.org.apache.xerces.internal.impl.xpath.XPath;

import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.util.ArrayList;

/**
 * Created by hussam on 09.02.17.
 *
 * Tokenizor, responsible for tokenizing your program.
 */
public class Tokenizor {

    /**
     * The main lexer. Takes a string which is the input file and returns a tokenized list.
     * @param input : The program.
     * @return : A list of the tokens of the program.
     */
    public static List<Token> lex(String input) {
        int x = 1; // x position in line.
        int y = 1; // Line number.

        // The tokens to return
        List<Token> tokens = new ArrayList<Token>();

        // Lexer logic begins here
        StringBuffer tokenPatternsBuffer = new StringBuffer();
        for (TokenType tokenType : TokenType.values())
            tokenPatternsBuffer.append(String.format("|(?<%s>%s)", tokenType.name(), tokenType.pattern));

        Pattern tokenPatterns = Pattern.compile(new String(tokenPatternsBuffer.substring( 1)));

        // Matching
        Matcher matcher = tokenPatterns.matcher(input);

        while(matcher.find()) {

            for (TokenType t : TokenType.values()) {

                if (t == TokenType.NEWLINE) {
                    y++;
                    x=1;
                    continue;
                }

                if (t == TokenType.WHITESPACE)
                    continue;

                if (matcher.group(t.name()) != null) {
                    tokens.add(new Token(t, matcher.group(t.name()), x, y));
                    x++;
                }
            }
        }
        return (ArrayList<Token>) tokens;
    }

}
