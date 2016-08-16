package CodeGen;

import java.util.*;
import Tree.*;

class StringLiterals implements TreeVisitor
{
    public StringLiterals () {
        stringDefs = new ArrayList<String> ();
    }

    public List<String> getDefinitions (ASTree root) {
        root.accept (this);
        return stringDefs;
    }

    public void visit (TypeID node){}
    public void visit (StringExpr node){}
    public void visit (IntExpr node){}
    public void visit (BoolExpr node){}
    public void visit (CharExpr node){}
    public void visit (IdExpr node){}
    public void visit (DirectAccess node){}


    public void enter (Program node){}
    public void exit  (Program node){}

    public void enter (TypeRA node){}
    public void exit  (TypeRA node){}

    public void enter (TypeP node){}
    public void exit  (TypeP node){}

    public void enter (AliasDecl node){}
    public void exit  (AliasDecl node){}

    public void enter (VarDecl node){}
    public void exit  (VarDecl node){}

    public void enter (ConstDecl node){}
    public void exit  (ConstDecl node){}

    public void enter (Assignment node){}
    public void exit  (Assignment node){}

    public void enter (ClassCtor node){}
    public void exit  (ClassCtor node){}

    public void enter (ConstrArgDec node){}
    public void exit  (ConstrArgDec node){}

    public void enter (ConstrArgs node){}
    public void exit  (ConstrArgs node){}

    public void enter (ClassDefBody node){}
    public void exit  (ClassDefBody node){}

    public void enter (ClassDef node){}
    public void exit  (ClassDef node){}

    public void enter (FuncDef node){}
    public void exit  (FuncDef node){}

    public void enter (FuncArg node){}
    public void exit  (FuncArg node){}

    public void enter (StmtWhile node){}
    public void exit  (StmtWhile node){}

    public void enter (DefIf node){}
    public void exit  (DefIf node){}

    public void enter (DefWhen node){}
    public void exit  (DefWhen node){}

    public void enter (ParExpr node){}
    public void exit  (ParExpr node){}

    public void enter (UnaryExpr node){}
    public void exit  (UnaryExpr node){}

    public void enter (BinaryExpr node){}
    public void exit  (BinaryExpr node){}

    public void enter (ObjDef node){}
    public void exit  (ObjDef node){}

    public void enter (FuncCall node){}
    public void exit  (FuncCall node){}

    public void enter (LetExprDef node){}
    public void exit  (LetExprDef node){}

    public void enter (LetExpr node){}
    public void exit  (LetExpr node){}

    public void enter (BlockExpr node){}
    public void exit  (BlockExpr node){}

    public void enter (Body node){}
    public void exit  (Body node){}

    public void enter (CondExpr node){}
    public void exit  (CondExpr node){}

    public void enter (BodyExpr node){}
    public void exit  (BodyExpr node){}

    private String nextStr () {
        return "@.str." + (idx++);
    }

    private int idx;
    private List<String> stringDefs;
}
