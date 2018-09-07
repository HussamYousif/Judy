import AST.*;
import Lexing.Token;
import Lexing.TokenClass;
import Lexing.Tokenizor;
import Parsing.ParseComponent;
import Parsing.ParseExpression;
import Util.TokenList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static Lexing.TokenType.SEMICOLON;

public class Main {

    public static void main(String[] args) throws Exception {
        /*String input = new String(Files.readAllBytes(Paths.get(args[0]))); // Thanks, Java 8 designers.
        String filename = args[0].replaceAll(".jdy","");



        if (input.equals(null)) {
            throw new IllegalArgumentException("Couldn't open file " + args[0]);
        }*/
        String testInput = "Component a: Start: MyFunctionCall(1+2); End End";



        TokenList l = new TokenList((testInput));
        Component c = ParseComponent.parse(l);
        System.out.println(c.toC());

        /*
        System.out.println("Starting Tokenizing... ");


        TokenList list = new TokenList(input);

        System.out.println("Starting The parsing... ");
        Component c = ParseComponent.parse(list);

        System.out.println("Starting the transformation.... ");
        String a = c.toC();

        System.out.println("The output: ");
        System.out.println(a);

        createAndWriteToFile(filename, a);*/
    }



    public static void createAndWriteToFile(String filename,String s) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter write = new PrintWriter(filename + ".cpp", "UTF-8");
        write.print(s);
        write.close();
    }


    public static void showTokens(ArrayList<Token> tokens) {
        tokens.stream().forEach(x -> System.out.println(x.toString()));
    }


}
