package Semantic;

import java.io.*;
import java.util.*;
import Parser.Position;

public class ErrorManager
{
    public ErrorManager () {
        errors = new HashSet<ErrorMessage> ();
    }
    public void addError (Position pos, String msg) {
        errors.add (new ErrorMessage (pos, msg));
    }

    public void addError (String msg) {
        errors.add (new ErrorMessage (null, msg));
    }

    public void printAll (PrintStream stream) {
        for (ErrorMessage msg : errors) {
            if (msg.pos != null) {
                stream.printf ("%d:%d: ", msg.pos.line, msg.pos.column);
            }
            stream.println (msg.msg);
        }
    }

    public boolean isEmpty () {
        return errors.isEmpty ();
    }

    private Set<ErrorMessage> errors;

    private class ErrorMessage
    {
        ErrorMessage (Position pos, String msg) {
            this.pos = pos;
            this.msg = msg;
        }
        Position pos;
        String msg;
    }

}