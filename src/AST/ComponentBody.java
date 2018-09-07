package AST;

import Util.UsefulStrings;

//import javax.jws.soap.SOAPBinding;
//import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * In reality the program itself is an instance of this class.
 *
 *
 */
public class ComponentBody {

    List<Procedure> procedures;
    List<Imports> imports;
    List<Variable> globals;
    List<Event> events;

    Start start;

    public ComponentBody() {
        procedures = new ArrayList<Procedure>();
        events = new ArrayList<Event>();
        globals = new ArrayList<Variable>();
        imports = new ArrayList<Imports>();
    }


    public void addStart(Start s) {
        this.start = s;
    }

    public void addProcedure(Procedure p) {
        procedures.add(p);
    }

    public void addEvent(Event e) {
        events.add(e);
    }

    public void addGlobal(Variable v) {
        globals.add(v);
    }

    public void addImport(Imports i) {
        imports.add(i);
    }

    public String formatEventsToMain() {
        String result = "";

        for(Event e : events) {
           result += UsefulStrings.getTab() + e.toMain() + UsefulStrings.getNL();
        }

        return result;
    }


    public String mainToC() {
        String signature = "int main()";
        String s = UsefulStrings.getTab() + "start(); " + UsefulStrings.getNL();
        String e = formatEventsToMain();

        String result = signature + "{" + UsefulStrings.getNL() + // void main() {
                s + UsefulStrings.getNL(); // start();

        // Will only be called iff there exists events.
         if (!events.isEmpty()) {
             result += UsefulStrings.getTab() + "while (1) {" + UsefulStrings.getNL() +
                    UsefulStrings.getTab() +  e +
                     UsefulStrings.getTab() + "}" + UsefulStrings.getNL();
         }
         result += "return 0;" + UsefulStrings.getNL();

         result += "}" ;
        return result;
    }


    public String toC() {
        String body = "";

        for(Imports i: imports) {
            body += i.toC() + UsefulStrings.getNL();
        }

        for(Variable global : globals) {
            body += global.toC() ;
        }



        for (Procedure p : procedures) {
            body += p.toC();
        }

        for (Event e : events) {
            body += e.toC() ;
        }

        body += start.toC();

        body += mainToC();

        return body;
    }
}
