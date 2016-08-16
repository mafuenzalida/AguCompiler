package Printer;

import java.io.*;
import Tree.*;

public class TreePrinter implements TreeVisitor
{
    public TreePrinter (PrintStream stream) {
        this.stream = stream;
        depth = 0;
    }

    public void print (ASTree root) {
        root.accept (this);
    }


    public void visit (TypeID node) {
        println ("TypeID <<", node.getName(), ">>");
    }

    public void visit (DirectAccess node) {
        println ("Attribute <<", node.getName(), ">>");
    }

    public void visit (StringExpr node) {
        println ("String <<", node.getName(), ">>");
    }
    public void visit (IntExpr node) {
        println ("Int <<", node.getName(), ">>");
    }
    public void visit (CharExpr node) {
        println ("Char <<", node.getName(), ">>");
    }
    public void visit (BoolExpr node) {
        println ("Bool <<", node.getName(), ">>");
    }
    public void visit (IdExpr node) {
        println ("Id <<", node.getName(), ">>");
    }


    public void enter (Program node) {
        println ("Program");
        incrDepth ();
    }
    public void exit  (Program node) {
        decrDepth ();
    }
    public void enter (TypeRA node) {
        println ("type -> type");
        incrDepth ();
    }
    public void exit  (TypeRA node) {
        decrDepth ();
    }
    public void enter (TypeP node) {
        println ("( type )");
        incrDepth ();
    }
    public void exit (TypeP node) {
        decrDepth ();
    }
    public void enter (AliasDecl node) {
        println("Alias <<", node.getName(), ">>");
        incrDepth ();
    }
    public void exit (AliasDecl node) {
        decrDepth();
    }
    public void enter (VarDecl node) {
        println("Var <<", node.getName(), ">>");
        incrDepth ();
    }
    public void exit (VarDecl node) {
        decrDepth();
    } 
    public void enter (ConstDecl node) {
        println("Constant <<", node.getName(), ">>");
        incrDepth();
    }
    public void exit (ConstDecl node) {
        decrDepth();
    }
    public void enter (Assignment node) {
        StringBuffer string = new StringBuffer();
        string.append("Assign ");
        for(String name: node.getNames()) {
            string.append("<<" + name + ">> ");
        }
        println(string.toString());
        incrDepth();
    }
    public void exit (Assignment node) {
        decrDepth();
    }

    public void enter (ClassDef node) {
        StringBuffer string = new StringBuffer();
        string.append(node.getParameter());
        if(string.toString() != "")
            string.append(" ");
        string.append("Class <<" + node.getName() + ">> ");
        println(string.toString());
        incrDepth();
    }
    public void exit (ClassDef node) {
        decrDepth();
    }
    public void enter (ClassDefBody node) {
        println("Def Body");
        incrDepth();
    }
    public void exit (ClassDefBody node) {
        decrDepth();
    }
    public void enter (ClassCtor node) {
        println("Ctor <<", node.getName(), ">>");
        incrDepth();
    }
    public void exit (ClassCtor node) {
        decrDepth();
    }
    public void enter (ConstrArgs node) {
        println("Arguments");
        incrDepth();
    }
    public void exit (ConstrArgs node) {
        decrDepth();
    }
    public void enter (ConstrArgDec node) {
        StringBuffer string = new StringBuffer();
        string.append(node.getParameter());
        if(string.toString() != "")
            string.append(" ");
        string.append("Argument <<" + node.getName() + ">> ");
        println(string.toString());
        incrDepth();
    }
    public void exit (ConstrArgDec node) {
        decrDepth();
    }

    public void enter (FuncDef node) {
        println("Function <<", node.getName(), ">>");
        incrDepth();
    }
    public void exit (FuncDef node) {
        decrDepth();
    }

    public void enter (FuncArg node) {
        println("Arg <<", node.getName(), ">>");
        incrDepth();
    }
    public void exit (FuncArg node) {
        decrDepth();
    }

    public void enter (StmtWhile node) {
        println("While");
        incrDepth();
    }
    public void exit (StmtWhile node) {
        decrDepth();
    }

    public void enter (DefIf node) {
        println("If");
        incrDepth();
    }
    public void exit (DefIf node) {
        decrDepth();
    }

    public void enter (DefWhen node) {
        println("When");
        incrDepth();
    }
    public void exit (DefWhen node) {
        decrDepth();
    }

    public void enter (ParExpr node) {
    }
    public void exit (ParExpr node) {
    }

    public void enter (UnaryExpr node) {
        println(node.getOperator());
        incrDepth();
    }
    public void exit (UnaryExpr node) {
        decrDepth();
    }

    public void enter (BinaryExpr node) {
        println(node.getOperator());
        incrDepth();
    }
    public void exit (BinaryExpr node) {
        decrDepth();
    }

    public void enter (ObjDef node) {
        println("Object <<", node.getName(), ">>");
        incrDepth();
    }
    public void exit (ObjDef node) {
        decrDepth();
    }

    public void enter (FuncCall node) {
        println("FuncCall or attribute access");
        incrDepth();
    }
    public void exit (FuncCall node) {
        decrDepth();
    }

    public void enter (LetExprDef node) {
        println("Let");
        incrDepth();
    }
    public void exit (LetExprDef node) {
        decrDepth();
    }

    public void enter (LetExpr node) {
        println("Let_Expr <<", node.getName(), ">>");
        incrDepth();
    }
    public void exit (LetExpr node) {
        decrDepth();
    }

    public void enter (BlockExpr node) {
        incrDepth();
    }
    public void exit (BlockExpr node) {
        decrDepth();
    }

    public void enter (Body node) {
        println("Body");
        incrDepth();
    }
    public void exit (Body node) {
        decrDepth();
    }

    public void enter (CondExpr node) {
        println("Condition");
        incrDepth();
    }
    public void exit (CondExpr node) {
        decrDepth();
    }

    public void enter (BodyExpr node) {
        println(node.getName());
        incrDepth();
    }
    public void exit (BodyExpr node) {
        decrDepth();
    }

    private void incrDepth () {
        depth++;
    }
    private void decrDepth () {
        depth--;
    }

    private void println (String... msg) {
        for (int i=0; i< depth; ++i) {
            stream.print (TAB);
        }
        for (int i=0; i<msg.length; ++i) {
            stream.print (msg[i]);
        }
        stream.println ();
    }

    private PrintStream stream;
    private int depth;
    private static final String TAB = "    ";
}
